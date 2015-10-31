package nl.m4jit.framework.wsaccess.wswrapper;

/**
 *
 * @author joris
 */
public class XMLWSWrapperException extends Exception{
    
    private Exception innerException;
    
    public XMLWSWrapperException(Exception innerException){
        
        this.innerException = innerException;
    }
}
