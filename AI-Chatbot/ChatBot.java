import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;

public class ChatBot extends JFrame {

    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;
    JButton clearButton;

    public ChatBot() {

        setTitle("AI ChatBot");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        sendButton = new JButton("Send");
        clearButton = new JButton("Clear Chat");

        buttonPanel.add(sendButton);
        buttonPanel.add(clearButton);

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        // Welcome Message
        chatArea.append(" Hello! I am your AI ChatBot.\n");
        chatArea.append(" Ask me anything.\n");
        chatArea.append(" Try commands like google, youtube, github, time, date, java, internship.\n\n");

        // Send Button Action
        sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                sendMessage();
            }
        });

        // Enter Key Action
        inputField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                sendMessage();
            }
        });

        // Clear Chat Action
        clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                chatArea.setText("");
            }
        });

        setVisible(true);
    }

    public void sendMessage() {

        String userMessage = inputField.getText().trim();

        if (userMessage.isEmpty()) {
            return;
        }

        chatArea.append("You: " + userMessage + "\n");

        String botResponse = getBotResponse(userMessage);

        chatArea.append("Bot: " + botResponse + "\n\n");

        inputField.setText("");
    }

    public String getBotResponse(String message) {

        message = message.toLowerCase();

        // Greetings
        if (message.contains("hello") || message.contains("hi")) {

            return "Hello! Nice to meet you.";
        }

        // How are you
        else if (message.contains("how are you")) {

            return "I am doing great. Thanks for asking!";
        }

        // Name
        else if (message.contains("your name")) {

            return "I am a Java AI ChatBot.";
        }

        // Time
        else if (message.contains("time")) {

            return "Current Time: " + LocalTime.now().withNano(0);
        }

        // Date
        else if (message.contains("date")) {

            return "Today's Date: " + LocalDate.now();
        }

        // Java
        else if (message.contains("java")) {

            return "Java is a powerful object-oriented programming language.";
        }

        // Internship
        else if (message.contains("internship")) {

            return "Internships help students gain practical experience.";
        }

        // Joke
        else if (message.contains("joke")) {

            return "Why do programmers prefer dark mode? Because light attracts bugs!";
        }

        // Google Search
        else if (message.startsWith("google ")) {

            String query = message.replace("google ", "");

            openWebsite(
                "https://www.google.com/search?q="
                        + query.replace(" ", "+")
            );

            return "Opening Google search for: " + query;
        }

        // YouTube Search
        else if (message.startsWith("youtube ")) {

            String query = message.replace("youtube ", "");

            openWebsite(
                "https://www.youtube.com/results?search_query="
                        + query.replace(" ", "+")
            );

            return "Opening YouTube search for: " + query;
        }

        // GitHub Search
        else if (message.startsWith("github ")) {

            String query = message.replace("github ", "");

            openWebsite(
                "https://github.com/search?q="
                        + query.replace(" ", "+")
            );

            return "Opening GitHub search for: " + query;
        }

        // Open Google
        else if (message.contains("open google")) {

            openWebsite("https://www.google.com");

            return "Opening Google...";
        }

        // Open YouTube
        else if (message.contains("open youtube")) {

            openWebsite("https://www.youtube.com");

            return "Opening YouTube...";
        }

        // Open GitHub
        else if (message.contains("open github")) {

            openWebsite("https://github.com");

            return "Opening GitHub...";
        }

        // Bye
        else if (message.contains("bye")) {

            return "Goodbye! Have a great day.";
        }

        // Default Response
        else {

            return "Sorry, I don't understand that yet.";
        }
    }

    // Method to Open Websites
    public void openWebsite(String url) {

        try {

            Desktop.getDesktop().browse(new URI(url));
        }

        catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Unable to open browser.");
        }
    }

    public static void main(String[] args) {

        new ChatBot();
    }
}
    

