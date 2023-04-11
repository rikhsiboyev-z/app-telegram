package frontend;

import backend.controller.*;
import backend.model.User;
import backend.payload.ChatRecord;
import backend.payload.MessageRecord;
import backend.payload.UserRecord;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FrontUI {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static UserController userController = new UserControllerImpl();
    static ChatController chatController = new ChatControllerImpl();
    static MessageController messageController = new MessageControllerImpl();
    static UserRecord currentUser = null;

    public static void main(String[] args) {

        User user1 = new User("1", "Sherbek", "Varabiskoy", "1234");
        User user2 = new User("2", "Sherqozi", "Varabiskoy1", "1234");
        User user3 = new User("3", "Sherali", "Varabiskoy2", "1234");
        userController.add(user1);
        userController.add(user2);
        userController.add(user3);

        System.out.println("-------------App Telegram ------------");

        menu:
        while (true) {
            regMenu();
            String regOption = getConsole("Select => ");

            switch (regOption) {
                case "1" -> signUp();
                case "2" -> signIn();
                case "q" -> System.exit(0);
            }

            if (currentUser == null)
                continue;

            while (true) {
                mainMenu();
                String mainOption = getConsole("Select => ");

                switch (mainOption) {
                    case "1" -> chatMenu();
                    case "2" -> settings();
                    case "3" -> deleteChat();
                    case "4" -> createChat();
                    case "q" -> {
                        currentUser = null;
                        continue menu;
                    }
                }
            }


        }


    }

    private static void createChat() {
        List<UserRecord> allUsers = userController.getAllUsers();

        for (int i = 0; i < allUsers.size(); i++) {
            if (random.nextBoolean())
                System.out.println((i + 1) + "👩=> " + allUsers.get(i));
            else
                System.out.println((i + 1) + "👨=> " + allUsers.get(i));
        }

        String index = getConsole("Select User => ");
        UserRecord userRecord = allUsers.get(Integer.parseInt(index) - 1);
        ChatRecord chatRecord = new ChatRecord(String.valueOf(random.nextInt()), currentUser, userRecord);
        chatController.add(chatRecord);
        System.out.println();
    }

    private static void deleteChat() {
    }

    private static void settings() {
        setingMenu();
        String setng = getConsole("Select =>");
        switch (setng) {
            case "1" -> {

            }

        }

    }

    private static void chatMenu() {
        System.out.println("\n---------------Chats----------------\n");

        List<ChatRecord> allChats = chatController.getAllChatsByUserId(currentUser.id());

        if (!allChats.isEmpty()) {
            for (int i = 0; i < allChats.size(); i++) {
                ChatRecord chatRecord = allChats.get(i);
                if (chatRecord.firstSide().id().equals(currentUser.id()))
                    System.out.println((i + 1) + "💌 => " + chatRecord.secondSide());
                else
                    System.out.println((i + 1) + "💌 => " + chatRecord.firstSide());
            }
        } else {
            System.out.println("Noo chats");
            return;
        }

        String index = getConsole("Select => ");
        ChatRecord chatRecord = allChats.get(Integer.parseInt(index) - 1);

        chatting(chatRecord);

    }

    private static void chatting(ChatRecord chatRecord) {
        if (chatRecord.firstSide().id().equals(currentUser.id()))
            System.out.println("------------" + chatRecord.secondSide().username() + "------------");
        else
            System.out.println("------------" + chatRecord.firstSide().username() + "------------");

        List<MessageRecord> allMessages = messageController.getAllMessagesByChatId(chatRecord.id());

        for (int i = 0; i < allMessages.size(); i++) {
            MessageRecord messageRecord = allMessages.get(i);
            if (messageRecord.senderId().equals(currentUser.id()))
                System.out.println((i + 1) + "✅=> " + messageRecord.messageBody());
            else
                System.out.println((i + 1) + "🛑=> " + messageRecord.messageBody());
        }

        System.out.println("1 => SendMessage");
        System.out.println("2 => EditMessage");
        System.out.println("3 => DeleteMessage");
        System.out.println("q => Exit");
        String messageOption = getConsole("Select => ");

        switch (messageOption) {
            case "1" -> sendMessage(chatRecord);
            case "2" -> editMessage(chatRecord, allMessages);
            case "3" -> deleteMessage(chatRecord, allMessages);
            case "q" -> {
                return;
            }
        }
        System.out.println("-------------------------------");
        chatting(chatRecord);
    }

    private static void deleteMessage(ChatRecord chatRecord, List<MessageRecord> allMessages) {
        String number = getConsole("Select Message Number to delet");
        MessageRecord messageRecord = allMessages.get(Integer.parseInt(number) - 1);

        messageController.delet(messageRecord.id());


    }

    private static void editMessage(ChatRecord chatRecord, List<MessageRecord> allMessages) {


        String number = getConsole("Select Message Number to edit");

        MessageRecord messageRecord = allMessages.get(Integer.parseInt(number) - 1);

        String nexText = getConsole("Enter New Text to replace");

        messageController.edit(messageRecord.id(), nexText);

    }

    private static void sendMessage(ChatRecord chatRecord) {
        String msg = getConsole("Enter Message => ");
        MessageRecord messageRecord = new MessageRecord(null, chatRecord.id(), msg, currentUser.id());
        messageController.add(messageRecord);
        System.out.println("Successfully");
    }

    private static void mainMenu() {
        System.out.println("1💌 = > Chatting");
        System.out.println("2💌 = > Setting");
        System.out.println("3💌 = > Detete Chat");
        System.out.println("4💌 = > Create Chat");
    }

    private static void signIn() {
        List<UserRecord> allUsers = userController.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            System.out.println((i + 1) + "✅ => " + allUsers.get(i));
        }

        String index = getConsole("Select => ");
        UserRecord userRecord = allUsers.get(Integer.parseInt(index) - 1);

        String password = getConsole("Enter Your Password");

        boolean m1 = userController.checkPassword(userRecord.id(), password);
        if (!m1) {
            System.out.println("User Not Found Or Incorrect Password");
            return;
        }
        currentUser = userRecord;
        System.out.println("successfull");
    }

    private static void signUp() {
        User user = new User(String.valueOf(random.nextInt()), "Sherbek", "Ajdar", "1234");
        currentUser = userController.add(user);
        System.out.println("Successful");
    }

    private static String getConsole(String s) {
        System.out.print(s);
        return scanner.nextLine();
    }


    private static void regMenu() {
        System.out.println("1 => Sign Up");
        System.out.println("2 => Sign In");
        System.out.println("q => Exit");
    }

    private static void setingMenu() {
        System.out.println("1 => UserName ozgartirish ");
        System.out.println("2 => Password ozgartirish");
    }

}
