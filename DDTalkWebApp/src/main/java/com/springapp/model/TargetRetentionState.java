package com.springapp.model;

/**
 * Created by peter on 9/5/15.
 */

//retention states as follows:
//  'NA', no retention policy for this target
//  'NM', target has retention policy but is not mastered yet; if fails retention, will be reset to this state
//  'MW', target has retention policy and is mastered but still in waiting period
//  'MM', target has retention policy and is mastered and retention retest was mastered

public class TargetRetentionState {
    static public String NO_RETENTION_POLICY            =   "N";
    static public String HAS_RETENTION_POLICY           =   "Y";
    static public String RETENTION_IN_WAIT_PERIOD       =   "W";
    static public String RETENTION_RETEST_READY         =   "R";
    static public String RETENTION_MASTERED             =   "M";

    static public boolean isMastered(String retentionState) {
        return retentionState.equalsIgnoreCase(RETENTION_MASTERED);
    }

    static public boolean isRetentionInWaitingPeriod(String retentionState) {
        return retentionState.equalsIgnoreCase(RETENTION_IN_WAIT_PERIOD);
    }

    static public boolean isRetentionRetestReady(String retentionState) {
        return retentionState.equalsIgnoreCase(RETENTION_RETEST_READY);
    }

}
