# Java Handwritten Digit Recognizer (No Libraries)

This project allows a user to draw a digit using a mouse and uses a custom AI recognition system to identify it — built completely from scratch using core Java (no ML libraries, no neural nets).

## ✨ Features
- Draw digits live with your mouse
- Automatically trims and processes input images
- Extracts multiple hand-designed features
- Aggregates prediction results with voting
- Works with or without GUI input

## 🖥️ How to Run

1. Open the project in any Java IDE (Eclipse, IntelliJ, etc.)
2. Run `DigitRecognizerUI.java`
3. Draw a digit and click **Predict**
4. The predicted result will appear above the canvas

## 📁 Folder Structure

- `src/HandWritten` — Image processing and reduction logic
- `src/Predictions` — Voting and prediction engine
- `src/ui` — GUI interface
- `src/Tests` — Optional test classes
- `data/` — (Optional) Sample training image directory

## 🔧 Built With
- Java SE
- AWT/Swing for UI
- No external dependencies or ML libraries

## 🤖 Why This Project?
This was built as a demonstration of AI running entirely on logic-based heuristics. It mimics digit classification similar to MNIST, but using optimized hardware-friendly techniques. Future versions may deploy this model on an FPGA board.
