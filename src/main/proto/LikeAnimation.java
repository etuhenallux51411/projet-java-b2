package main.proto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LikeAnimation extends JFrame {

    private JPanel postPanel;
    private JLabel userLabel;
    private JLabel postContent;
    private JButton likeButton;
    private JButton commentButton;
    private JPanel interactionPanel;
    private JLabel likeLabel;
    private JLabel commentLabel;
    private ImageIcon heartIcon;
    private ImageIcon commentIcon;

    public LikeAnimation() {
        setTitle("Twitter Post Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel pour le post
        postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        postPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postPanel.setBackground(Color.WHITE);

        // Utilisateur
        userLabel = new JLabel("The Great Khali - @greatkhali - 2h");
        postPanel.add(userLabel, BorderLayout.NORTH);

        // Contenu du post
        postContent = new JLabel("Java est un langage de programmation et une plate-forme de calcul lancé par Sun Microsystems en 1995. Depuis ses débuts modestes, Java a beaucoup évolué. A l'heure actuelle,\n" +
                "une grande partie du monde numérique dépend de Java : de nombreux services et applications reposent sur cette plate-forme fiable. Java est utilisé dans de nombreux domaines, notamment dans les applications " );
        postPanel.add(postContent, BorderLayout.CENTER);

        // Boutons d'interaction (Like et Comment)
        interactionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Bouton "Like"
        heartIcon = new ImageIcon(getClass().getResource("proto/like.png"));
        likeButton = new JButton(heartIcon);
        likeButton.setContentAreaFilled(false);
        likeButton.setFocusPainted(false);
        likeButton.setBorderPainted(false);
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLike();
            }
        });
        interactionPanel.add(likeButton);

        // Label pour le compteur de likes
        likeLabel = new JLabel("0 likes");
        interactionPanel.add(likeLabel);

        // Bouton "Comment"
        commentIcon = new ImageIcon(getClass().getResource("proto/comment.png"));
        commentButton = new JButton(commentIcon);
        commentButton.setContentAreaFilled(false);
        commentButton.setFocusPainted(false);
        commentButton.setBorderPainted(false);
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComment();
            }
        });
        interactionPanel.add(commentButton);

        postPanel.add(interactionPanel, BorderLayout.SOUTH);

        add(postPanel, BorderLayout.CENTER);

        setSize(400, 300);
        setVisible(true);
    }

    // Ajoute un like
    private void addLike() {
        int currentLikes = Integer.parseInt(likeLabel.getText().split(" ")[0]);
        currentLikes++;
        likeLabel.setText(currentLikes + " likes");

        // Lancer l'animation de like dans un nouveau thread
        new Thread(new LikeAnimationThread()).start();
    }

    // Ajoute un commentaire
    private void addComment() {
        String comment = JOptionPane.showInputDialog(this, "Enter your comment:");
        if (comment != null && !comment.isEmpty()) {
            if (commentLabel == null) {
                commentLabel = new JLabel("<html>" + comment + "</html>");
                postPanel.add(commentLabel, BorderLayout.EAST);
            } else {
                String currentText = commentLabel.getText();
                commentLabel.setText("<html>" + currentText + "<br>" + comment + "</html>");
            }
            postPanel.revalidate();
            postPanel.repaint();
        }
    }

    // Thread pour l'animation de like
    private class LikeAnimationThread implements Runnable {
        @Override
        public void run() {
            try {
                int size = heartIcon.getIconWidth() + 10;
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(50);
                    size += 3;
                    ImageIcon resizedHeartIcon = new ImageIcon(heartIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
                    likeButton.setIcon(resizedHeartIcon);
                }
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(50);
                    size -= 3;
                    ImageIcon resizedHeartIcon = new ImageIcon(heartIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
                    likeButton.setIcon(resizedHeartIcon);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LikeAnimation();
            }
        });
    }
}
