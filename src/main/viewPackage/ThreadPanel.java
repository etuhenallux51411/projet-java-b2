package main.viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThreadPanel extends JPanel {
    private final JLabel likeLabel;
    private static final int NORMAL_SIZE = 50;
    private static final int LARGE_SIZE = 100;
    private static final int ANIMATION_DURATION = 2000; // 2 seconds
    private static final int FRAME_RATE = 30; // 30 frames per second

    private boolean isAnimating = false;

    public ThreadPanel() {
        setLayout(new BorderLayout());

        ImageIcon likeIcon = new ImageIcon("src/main/assets/heart.png");
        likeIcon.setImage(likeIcon.getImage().getScaledInstance(NORMAL_SIZE, NORMAL_SIZE, Image.SCALE_DEFAULT));
        likeLabel = new JLabel(likeIcon);

        add(likeLabel, BorderLayout.CENTER);

        likeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isAnimating) {
                    isAnimating = true;
                    animateHeart();
                }
            }
        });
    }

    private void animateHeart() {
        Timer enlargeTimer = new Timer(1000 / FRAME_RATE, null);
        Timer shrinkTimer = new Timer(1000 / FRAME_RATE, null);

        enlargeTimer.addActionListener(e -> {
            int elapsed = ((Timer) e.getSource()).getInitialDelay();
            float progress = (float) elapsed / ANIMATION_DURATION;
            int newSize = (int) (NORMAL_SIZE + (LARGE_SIZE - NORMAL_SIZE) * progress);
            if (newSize > LARGE_SIZE) newSize = LARGE_SIZE;

            updateHeartSize(newSize);

            if (elapsed >= ANIMATION_DURATION) {
                enlargeTimer.stop();
                shrinkTimer.setInitialDelay(0);
                shrinkTimer.start();
            } else {
                enlargeTimer.setInitialDelay(elapsed + 1000 / FRAME_RATE);
            }
        });

        shrinkTimer.addActionListener(e -> {
            int elapsed = ((Timer) e.getSource()).getInitialDelay();
            float progress = (float) elapsed / ANIMATION_DURATION;
            int newSize = (int) (LARGE_SIZE - (LARGE_SIZE - NORMAL_SIZE) * progress);
            if (newSize < NORMAL_SIZE) newSize = NORMAL_SIZE;

            updateHeartSize(newSize);

            if (elapsed >= ANIMATION_DURATION) {
                shrinkTimer.stop();
                isAnimating = false;
            } else {
                shrinkTimer.setInitialDelay(elapsed + 1000 / FRAME_RATE);
            }
        });

        enlargeTimer.setInitialDelay(0);
        enlargeTimer.start();
    }

    private void updateHeartSize(int size) {
        ImageIcon resizedIcon = new ImageIcon("src/main/assets/heart.png");
        resizedIcon.setImage(resizedIcon.getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT));
        likeLabel.setIcon(resizedIcon);
        revalidate();
        repaint();
    }
}
