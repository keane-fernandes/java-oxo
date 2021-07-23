import OXOExceptions.OXOMoveException;

public class OXOTester
{
    public OXOTester()
    {
    }

    public static void main(String args[])
    {
        boolean assertionsEnabled = false;
        assert(assertionsEnabled = true);
        if (assertionsEnabled) {
            testInput();
            System.out.println("SUCCESS: All tests passed !!!");
        }
        else {
            System.out.println("You MUST run java with assertions enabled (-ea) to test your program !");
        }
    }

    public static void testInput()
    {
        OXOModel model = new OXOModel(3,3,3);
        model.addPlayer(new OXOPlayer('X'));
        model.addPlayer(new OXOPlayer('O'));
        OXOController controller = new OXOController(model);

        try{
            controller.handleIncomingCommand("a1");
            controller.handleIncomingCommand("a2");
            controller.handleIncomingCommand("a3");
            controller.handleIncomingCommand("b1");
            controller.handleIncomingCommand("b2");
            controller.handleIncomingCommand("b3");
            model.setWinThreshold(4);
            model.addPlayer(new OXOPlayer('Z'));
            controller.handleIncomingCommand("c1");
            controller.handleIncomingCommand("c2");
            controller.handleIncomingCommand("c3");
        }
        catch (OXOMoveException e){
            System.out.println(e);
        }
        assert(model.getWinner() == null);
        assert(model.getPlayerByNumber(2).getPlayingLetter() == 'Z');
        assert(model.getCellOwner(2,2).getPlayingLetter() == 'Z');
    }
}
