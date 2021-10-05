package OXOExceptions;

public class InvalidIdentifierLengthException extends InvalidIdentifierException
{
    int length;

    public InvalidIdentifierLengthException(int length)
    {
        super();
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString() +
                "The length of the identifier should be 2 characters long. You have entered " + length + " character(s).";
    }
}
