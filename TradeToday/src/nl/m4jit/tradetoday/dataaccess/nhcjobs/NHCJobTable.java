package nl.m4jit.tradetoday.dataaccess.nhcjobs;

import java.util.*;
import nl.m4jit.framework.sqlaccess.*;

public class NHCJobTable extends AbstractTable<AbstractGateway> {

    private final String SELECTSTRING = "SELECT nj FROM NHCJob nj";
    private static NHCJobTable instance;

    private NHCJobTable() {
    }

    public List<NHCJobGateway> findToBeApproved() {

        return executeQuery(SELECTSTRING, "WHERE nj.jstate = 1");
    }

    public List<NHCJobGateway> findToBePlanned() {

        return executeQuery(SELECTSTRING, "WHERE nj.jstate = 2");
    }

    public List<NHCJobGateway> findToBoExecuted() {

        return executeQuery(SELECTSTRING, "WHERE nj.jstate = 3");
    }

    public List<NHCJobGateway> findWithHelpSignals() {

        return executeQuery(SELECTSTRING, "WHERE nj.jstate = 0 and nj.helpSignals <> '' and nj.helpSignalsAction is null");
    }

    public List<NHCJobGateway> findWithHelpSignalsByMember(int membernumber) {

        return executeQuery(SELECTSTRING, "WHERE nj.jstate = 0 and nj.helpSignals <> '' and nj.member.number = " + membernumber);
    }

    public List<NHCJobGateway> findByMember(int membernumber) {

        return executeQuery(SELECTSTRING, "WHERE nj.jstate = 0 and nj.member.number = " + membernumber);
    }

    public static NHCJobTable getInstance() {

        if (instance == null) {

            instance = new NHCJobTable();
        }

        return instance;
    }
}
