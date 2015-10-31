package nl.m4jit.tradetoday.domainlogic.users;

/**
 *
 * @author joris
 */
public class Right {

    private char character;
    private String name;

    public Right(char character, String name){

        this.character = character;
        this.name = name;
    }

    public char getCharacter() {

        return character;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {

        return name;
    }
}