package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.imageio.ImageIO;

import HandWritten.*; // Import your core logic
import Predictions.*; // Import your prediction logic

public class DigitRecognizerUI extends JFrame {
    private DrawPanel drawPanel;
    private JLabel resultLabel;

    public DigitRecognizerUI() {
        setTitle("Handwritten Digit Recognizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLayout(new BorderLayout());

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton clearButton = new JButton("Clear");
        JButton predictButton = new JButton("Predict");
        //JButton uploadButton = new JButton("Upload Image");

        clearButton.addActionListener(e -> drawPanel.clear());
        predictButton.addActionListener(e -> predictDigit(drawPanel.getImage()));
        //uploadButton.addActionListener(e -> uploadImage());

        buttonPanel.add(clearButton);
        buttonPanel.add(predictButton);
        //buttonPanel.add(uploadButton);

        add(buttonPanel, BorderLayout.SOUTH);

        resultLabel = new JLabel("Draw a digit or upload an image, then click Predict.", SwingConstants.CENTER);
        add(resultLabel, BorderLayout.NORTH);
    }

    private void predictDigit(BufferedImage img) {
        // 1. Save the image as a PNG file
        File saved = saveImageToFile(img, "user_input.png");
        if (saved == null) {
            resultLabel.setText("Failed to save image for prediction.");
            return;
        }

        // this is the reults...
        resultLabel.setText("Image saved as user_input.png. Ready to predict!");
        ImageProcessor.main(null); //the null means nothing. this does all the work and delegates it to PredictionResult.java which displays the results in the console
        
        //***********chat gpt help for gui response***********************
        try (BufferedReader reader = new BufferedReader(new FileReader("result.txt"))) {
            String line;
            String prediction = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ULTIMATE RESULT =")) {
                    prediction = line.replace("ULTIMATE RESULT =", "").trim();
                    break;
                }
            }

            if (prediction != null) {
            	resultLabel.setText("<html><div style='font-size:24px; font-weight:bold;'>Prediction: " + prediction + "</div></html>");
            } else {
                resultLabel.setText("Prediction could not be read.");
            }
        } catch (Exception ex) {
            resultLabel.setText("Couldn't load result.");
        }

        //*************chat gpt help **************************************
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(file);
                drawPanel.setImage(img);
                resultLabel.setText("Image loaded. Click Predict.");
            } catch (Exception ex) {
                resultLabel.setText("Failed to load image.");
            }
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DigitRecognizerUI ui = new DigitRecognizerUI();
            ui.setVisible(true);
        });
    }

    // Inner class for drawing panel
    private static class DrawPanel extends JPanel {
        private BufferedImage image;
        private Graphics2D g2;
        private int prevX, prevY;

        public DrawPanel() {
            setPreferredSize(new Dimension(280, 280));
            setBackground(Color.WHITE);
            image = new BufferedImage(280, 280, BufferedImage.TYPE_INT_RGB);
            g2 = image.createGraphics();
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, 280, 280);
            g2.setColor(Color.BLACK);
            setDoubleBuffered(false);

            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    prevX = e.getX();
                    prevY = e.getY();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    int x = e.getX();
                    int y = e.getY();
                    g2.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); //change stroke thickness here
                    g2.drawLine(prevX, prevY, x, y);
                    repaint();
                    prevX = x;
                    prevY = y;
                }
            });
        }

        public void clear() {
            g2.setPaint(Color.WHITE);
            g2.fillRect(0, 0, 280, 280);
            g2.setPaint(Color.BLACK);
            repaint();
        }

        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage img) {
            g2.drawImage(img, 0, 0, 280, 280, null);
            repaint();
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
    }
    
    // Save the current image to a PNG file
    private File saveImageToFile(BufferedImage img, String filename) {
        try {
            File outputfile = new File(filename);
            ImageIO.write(img, "png", outputfile);
            return outputfile;
        } catch (Exception e) {
            resultLabel.setText("Failed to save image: " + e.getMessage());
            return null;
        }
    }
}