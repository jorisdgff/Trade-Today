package nl.m4jit.tradetoday.domainlogic.nhcjobs;

import java.util.Date;
import nl.m4jit.tradetoday.dataaccess.members.MemberGateway;
import nl.m4jit.tradetoday.dataaccess.nhcjobs.*;
import nl.m4jit.tradetoday.dataaccess.transactions.TransactionGateway;

public class NHCJobModule {

    private final NHCJobTable table;
    private static NHCJobModule instance;
    
    private NHCJobModule(){
    
        table = NHCJobTable.getInstance();
    }
    
    public NHCJobGateway create(MemberGateway member, NHCJobTypeGateway type, String description, boolean agree, Date creationDate, Date reactionDateDeadline){
     
        NHCJobGateway job = new NHCJobGateway();
        job.setMember(member);
        job.setType(type);
        job.setDescription(description);
        job.setAgree(agree);
        job.setCreationDate(creationDate);
        job.setReactionDateDeadline(reactionDateDeadline);
        job.setState(1);
        table.insert(job);
        return job;
    }
    
    public void approve(NHCJobGateway job, Date reactionDate, Date executionDateDeadline) {

        job.setReactionDate(reactionDate);
        job.setExecutionDateDeadline(executionDateDeadline);
        job.setState(2);
        table.update(job);
    }

    public void reject(NHCJobGateway job, Date reactionDate, String rejectionReason) {

        job.setReactionDate(reactionDate);
        job.setRejectionReason(rejectionReason);
        job.setState(0);
        table.update(job);
    }

    public void plan(NHCJobGateway job, Date executionDate) {

        job.setExecutionDate(executionDate);
        job.setState(3);
        table.update(job);
    }

    public void settle(NHCJobGateway job, String helpsignals, TransactionGateway transaction) {

        job.setHelpSignals(helpsignals);
        job.setTransaction(transaction);
        job.setState(0);
        table.update(job);
    }
    
    public void pass(NHCJobGateway job, String action){
        
        job.setHelpSignalsAction(action);
        table.update(job);
    }
    
    public static NHCJobModule getInstance(){
        
        if(instance == null){
            
            instance = new NHCJobModule();
        }
        
        return instance;
    }
}
