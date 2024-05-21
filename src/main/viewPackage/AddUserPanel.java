package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.controllerPackage.CountriesController;
import main.controllerPackage.UserController;
import main.exceptionPackage.*;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;
import main.validatorPackage.FormValidator;

public class AddUserPanel extends JPanel implements ActionListener, ItemListener {
    private static final String GENDER_MAN_STRING = "Homme";
    private static final String GENDER_WOMAN_STRING = "Femme";
    private static final String GENDER_OTHER_STRING = "Autre";
    private static final String[] GENDER_CHOICE = {GENDER_MAN_STRING, GENDER_WOMAN_STRING, GENDER_OTHER_STRING};
    private static final int TEXT_FIELD_COLUMNS = 20;
    private static final int LABEL_PADDING = 20;
    private static final int FIELD_PADDING = 5;

    private MainWindow mainWindow;
    private int nbFields = 0;
    private JLabel topLabel;
    private JTextField username;
    private JPasswordField password;
    private JTextField email;
    private JTextField phoneNumber;
    private JTextField street;
    private JTextField bio;
    private JCheckBox isAdmin;
    private JSpinner dateOfBirthSpinner;
    private SpinnerDateModel dateOfBirthModel;
    private JButton submit;
    private JComboBox<LocalityItem> zipCode;
    private JComboBox<String> country;
    private JComboBox<String> gender;
    private Dimension textFieldSize = new JTextField(TEXT_FIELD_COLUMNS).getPreferredSize();

    private CountriesController countriesController;
    private UserController userController;

    public AddUserPanel(MainWindow mainWindow) throws CountriesDAOException, ConnectionDataAccessException, LocalityException {
        countriesController = new CountriesController();
        userController = new UserController();

        this.mainWindow = mainWindow;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(LABEL_PADDING, LABEL_PADDING, LABEL_PADDING, LABEL_PADDING);

        topLabel = new JLabel("Création d'un utilisateur");
        gbc.gridx = 0;
        gbc.gridy = nbFields;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(topLabel, gbc);

        gbc.insets = new Insets(FIELD_PADDING, FIELD_PADDING, FIELD_PADDING, FIELD_PADDING);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        addField(gbc, "Champs Obligatoires *", new JLabel(""));
        addField(gbc, "Email *", email = new JTextField(TEXT_FIELD_COLUMNS));
        addField(gbc, "Nom d'utilisateur *", username = new JTextField(TEXT_FIELD_COLUMNS));
        addField(gbc, "Mot de passe *", password = new JPasswordField(TEXT_FIELD_COLUMNS));

        dateOfBirthModel = new SpinnerDateModel();
        dateOfBirthSpinner = new JSpinner(dateOfBirthModel);
        dateOfBirthSpinner.setEditor(new JSpinner.DateEditor(dateOfBirthSpinner, "dd/MM/yyyy"));
        dateOfBirthSpinner.setPreferredSize(textFieldSize);

        addField(gbc, "Date de naissance *", dateOfBirthSpinner);
        addField(gbc, "Rue et numéro *", street = new JTextField(TEXT_FIELD_COLUMNS));

        zipCode = new JComboBox<>();
        zipCode.setPreferredSize(textFieldSize);
        addField(gbc, "Code postal *", zipCode);

        addField(gbc, "Téléphone", phoneNumber = new JTextField(TEXT_FIELD_COLUMNS));
        addField(gbc, "Biographie", bio = new JTextField(TEXT_FIELD_COLUMNS));

        gender = new JComboBox<>(GENDER_CHOICE);
        gender.setPreferredSize(textFieldSize);
        addField(gbc, "Genre *", gender);

        country = new JComboBox<>(countriesController.getCountries().toArray(new String[0]));
        country.setPreferredSize(textFieldSize);
        country.addItemListener(this);
        addField(gbc, "Pays *", country);
        refreshLocalities();

        addField(gbc, "Admin *", isAdmin = new JCheckBox());

        submit = new JButton("Ajouter l'utilisateur");
        submit.setPreferredSize(textFieldSize);
        submit.addActionListener(this);
        addField(gbc, "", submit);

        setVisible(true);
    }

    private void addField(GridBagConstraints gbc, String labelText, Component component) {
        nbFields++;
        gbc.gridx = 0; // Texte a gauche
        gbc.gridy = nbFields; // Position du component
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1; // placement du component a droite du texte correspondant
        add(component, gbc);
    }

    private void refreshLocalities() throws LocalityException, ConnectionDataAccessException {
        List<LocalityModel> localities;
            localities = userController.getLocality(Objects.requireNonNull(country.getSelectedItem()).toString());
            zipCode.removeAllItems();
            List<LocalityItem> formattedLocalities = new ArrayList<>();
            if (localities.isEmpty()) {
                formattedLocalities.add(new LocalityItem("Aucune localité trouvée", -1));
            } else {
                for (LocalityModel locality : localities) {
                    formattedLocalities.add(new LocalityItem(locality.getCity() + " - " + locality.getZipCode(), locality.getCode()));
                }
            }

            for (LocalityItem formattedLocality : formattedLocalities) {
                zipCode.addItem(formattedLocality);
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (validateForm()) {
                UserModel user = new UserModel();
                Date dob = new Date(dateOfBirthModel.getDate().getTime());

                user.setEmail(email.getText());
                user.setUsername(username.getText());
                user.setPassword(new String(password.getPassword()));
                user.setDateOfBirth(dob);

                if (Objects.requireNonNull(gender.getSelectedItem()).equals(GENDER_MAN_STRING)) user.setGender('m');
                else if (Objects.requireNonNull(gender.getSelectedItem()).equals(GENDER_WOMAN_STRING)) user.setGender('w');
                else user.setGender('o');

                user.setStreetAndNumber(street.getText());

                if (phoneNumber.getText().isEmpty()) user.setPhoneNumber(null);
                else user.setPhoneNumber(phoneNumber.getText());

                if (bio.getText().isEmpty()) user.setBio(null);
                else user.setBio(bio.getText());

                user.setAdmin(isAdmin.isSelected());
                user.setHome(((LocalityItem) zipCode.getSelectedItem()).getLocalityId());

                try {
                    userController.createUser(user);
                    mainWindow.displayMessage("Utilisateur créé avec succès", "Succès");
                    mainWindow.switchPanel(mainWindow.getListingPanel());
                } catch (UserCreationException ex) {
                    mainWindow.displayError(ex.toString());
                }
            }
        }
    }

    private Boolean validateForm() {
        String emailText = email.getText();
        String usernameText = username.getText();
        String passwordText = new String(password.getPassword());
        String streetText = street.getText();
        LocalityItem selectedItem = (LocalityItem) zipCode.getSelectedItem();
        LocalDate dob = dateOfBirthModel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        int localityId = selectedItem != null ? selectedItem.getLocalityId() : -1;

        if (FormValidator.isOneStringEmpty(emailText, usernameText, passwordText, streetText) || localityId == -1) {
            mainWindow.displayError("Tous les champs obligatoires doivent être remplis");
            return false;
        }

        if (!FormValidator.isValidEmail(emailText)) {
            mainWindow.displayError("L'email n'est pas valide (format : x@x.x)");
            return false;
        }

        if (!FormValidator.validDateOfBirth(dob)) {
            mainWindow.displayError("La date de naissance n'est pas valide");
            return false;
        }
        return true;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == country) {
            try {
                refreshLocalities();
            } catch (ConnectionDataAccessException | LocalityException ex) {
                mainWindow.displayError(ex.toString());
            }
        }
    }
}
