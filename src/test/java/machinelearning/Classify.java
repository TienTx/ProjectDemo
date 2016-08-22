/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;
//

/**
 *
 * @author zOzDarKzOz
 */
public class Classify
{

    private static CategoryTrainingService ct = new CategoryTrainingServiceImpl();
    private static SentimentTrainingService st = new SentimentTrainingServiceImpl();

    private static void categoryTrain(String path)
    {
        if (ct.train(path)) {
            System.out.println("Trained");
        } else {
            System.out.println("Trainng fail!");
        }
    }

    private static void sentimentTrain(String path)
    {
        if (st.train(path)) {
            System.out.println("Trained");
        } else {
            System.out.println("Trainng fail!");
        }
    }

    private static void categoryClassify(String[] list)
    {
        try {
            int j = 1;
            for (String text : list) {
                String cat = ct.classify(text);
                System.out.println(j + "\t" + text + "\t" + cat);
                j++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void setimentClassify(String[] list)
    {
        try {
            int j = 1;
            for (String text : list) {
                String sent = st.classify(text);
                System.out.println(j + "\t" + text + "\t" + sent);
                j++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void classify(String[] list)
    {
        try {
            int j = 1;
            for (String text : list) {
                String cat = ct.classify(text);
                String sent = st.classify(text);
                System.out.println(j + "\t" + text + "\t" + cat + "\t" + sent);
                j++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        String[] list = {
            "Darwin fish bumper stickers and assorted other atheist "
            + "paraphernalia are available from the Freedom From Religion "
            + "Foundation in the US.",
            "If you are using PIXAR'S RenderMan 3D scene description language "
            + "for creating 3D worlds, please, help me.",
            "I am in charge of purchasing some computer software for a small office "
            + "and I have a few question about Microsoft's Office Pack.",
            "I have an NEC multisync 3d monitor for sale. great condition. "
            + "looks new. it is .28 dot pitch",
            "I must say, that the courtesy of a nod or a wave as I meet other "
            + "bikers while riding does a lot of good things to my mood...",
            "Hello, my friends and I are running the Homewood Fantasy Baseball "
            + "League (pure fantasy baseball teams).",
            "The Adams division race is certainly interesting this year.  "
            + "Here are important data to keep in mind in the eventuality "
            + "of a tie for a given standing position.",
            "The following are my thoughts on a meeting that I, Hugh Kelso, "
            + "and Bob Lilly had with an aide of Sen. Patty Murrays.  "
            + "We were there to discuss SSTO, and commercial space.  "
            + "This is how it went...",
            "What about guns with non-lethal bullets, like rubber or plastic bullets. "
            + "Would those work very well in stopping an attack?",
            "Someone posted a list of x number of alleged Bible contradictions.  "
            + "As Joslin said, most people do value quantity over quality.  "
            + "Dave Butler posted some good quality alleged contradictions "
            + "that are taking a long time to properly exegete."
        };
//        Classify.categoryTrain(Variable.CATEGORY_CLASSIFFIER_DATATRAIN_PATH);
//        Classify.sentimentTrain(Variable.SENTIMENT_CLASSIFFIER_DATATRAIN_PATH);
        Classify.classify(list);
    }
}
