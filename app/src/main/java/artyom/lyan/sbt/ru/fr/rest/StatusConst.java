package artyom.lyan.sbt.ru.fr.rest;

/**
 * Created by SBT-Lyan-AI on 30.12.2016.
 */
public enum StatusConst {

    SUCCESS("SUCCESS"),
    FAILURE("FAILURE"),
    WARNING("WARNING"),
    ERROR("ERROR"),
    NULL("NULL"),
    UNKNOWN("UNKNOWN");


    StatusConst(String status) {
        this.status = status;
    }

    public static StatusConst checkStatus(String status){
        if ("SUCCESS".equals(status)){
            return SUCCESS;
        }

        if ("FAILURE".equals(status)){
            return FAILURE;
        }

        if ("WARNING".equals(status)){
            return WARNING;
        }

        if ("ERROR".equals(status)){
            return ERROR;
        }

        if ("NULL".equals(status)){
            return NULL;
        }

        return UNKNOWN;
    }

    private String status;
}
