package ru.vicgoy;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Random;

public class FilmBot extends TelegramLongPollingBot {
 final static String START_COMMAND="/start";


    private static HashMap<String, String> chatIdLastCommand = new HashMap<>();
  private   String[] arrayBlack = {"A Girl Walks Home Alone at Night, 2014",
            "Dancer in the Dark, 2000",
            "Metropolis, 1927"};


  private   String[] arrayRed = {"Beyond the Black Rainbow, 2010",
            "doom generation",
            "neon demon",
            "Excision, 2012",
            "Crash, 1996",
            "Donnie Darko, 2001"
    };

   private String[] arrayOrange = {"To Walk Invisible: The Bronte Sisters, 2016",
            "The Bad Batch, 2016",
            "A Scanner Darkly, 2006",
            "Buffalo '66, 1997",
            "Natural Born Killers, 1994",
            "Twelve Monkeys, 1995",
            "We Need to Talk About Kevin, 2011"
    };

   private String[] arrayYellow = {"Caótica Ana, 2007",
            "Amour, 2012",
            "The Fall, 2006",
            "The Boy in the Striped Pyjamas, 2008",
            "Black Snake Moan, 2006",
            "The Sunset Limited , 2010",
            "What We Do in the Shadows, 2014"
    };

    private  String[] arrayGreen = {"Carnage, 2011",
            "La migliore offerta, 2012",
            "Le renard et l'enfant, 2007",
            "Il racconto dei racconti - Tale of Tales, 2016",
            "El laberinto del fauno, 2006"
    };


    private  String[] arraySkyBlue = {"Клык/Kynodontas, 2009",
            "Calvary, 2013",
            "La science des rêves? 2006",
            "The Straight Story, 1999",
            "Mommy, 2014",
            "Detachment, 2011"
    };

    private  String[] arrayDeepBlue = {"A Ghost Story, 2017",
            "Love, 2011",
            "Una pura formalità, 1993",
            "Raw, 2016",
            "Melancholia, 2011",
            "Triangle, 2009"
    };

    private  String[] arrayViolet = {"Repo! The Genetic Opera, 2008",
            "After Hours, 1985",
            "Alphaville, une étrange aventure de Lemmy Caution, 1965",
            "Blade Runner, 1982",
            "Brazil, 1985",
            "The Double, 2013",
            "The Jacket, 2014",};

    private String[] arrayPink = {"The Love Witch, 2016"};


    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage();
        String txt = msg.getText().toLowerCase();
        String chatId = msg.getChatId().toString();
        if (!chatIdLastCommand.containsKey(chatId)) chatIdLastCommand.put(chatId, " ");
        String lastCommand = chatIdLastCommand.get(chatId);
        switch (lastCommand) {
            case START_COMMAND:
                chooseColor(txt, msg);
                break;
            default:
                switch (msg.getText()) {
                    case START_COMMAND: {
                        chatIdLastCommand.put(chatId, START_COMMAND);
                        chooseColor(txt, msg);
                        break;
                    }
                    default:sendMsg(msg,"Введите комнду /start для начала");
                }
        }


    }

    public void chooseColor(String color, Message msg) {
        String chatId = msg.getChatId().toString();

        switch (color) {
            /*case "white": {
                sendMsg(msg, arrayWhite[new Random().nextInt(arrayWhite.length - 1)]);
                chatIdLastCommand.replace(chatId, " ");
                break;
            }*/
            case "orange": {
                choosenColorandNext(msg, arrayOrange);
                break;
            }

            case "black": {
                choosenColorandNext(msg, arrayBlack);
                break;
            }
            case "red": {
                choosenColorandNext(msg, arrayRed);
                break;
            }
            case "yellow": {
                choosenColorandNext(msg, arrayYellow);
                break;
            }
            case "green": {
                choosenColorandNext(msg, arrayGreen);
                break;
            }
            case "skyblue": {
                choosenColorandNext(msg, arraySkyBlue);
                break;
            }
            case "deepbluek": {
                choosenColorandNext(msg, arrayDeepBlue);
                break;
            }
            case "violet": {
                choosenColorandNext(msg, arrayViolet);
                break;
            }
            case "pink": {
                choosenColorandNext(msg, arrayPink);
                break;
            }
            default:
                sendMsg(msg, "Выбери цвет:\n" +
                        "red\n" +
                        "orange\n" +
                        "yellow\n" +
                        "green\n" +
                        "skyblue\n" +
                        "deepblue\n" +
                        "violet\n" +
                        "pink\n" +
                        "black\n");
        }

    }

    @Override
    public String getBotUsername() {
        return "LuscherBot";
    }

    @Override
    public String getBotToken() {
        return "535796655:AAF9NN3CnP2oSqlPzyp2xi8k4nfUaT2qfLs";
    }

    @SuppressWarnings("deprecation")
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void choosenColorandNext(Message msg, String[] arrrayOfFilms) {
        sendMsg(msg, arrrayOfFilms[new Random().nextInt(arrrayOfFilms.length - 1)]);
        chatIdLastCommand.replace(msg.getChatId().toString(), " ");

    }

    
}
