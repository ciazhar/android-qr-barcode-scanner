package com.ciazhar.qrbarcodescanner.model;

/**
 * Created by ciazhar on 21/12/17.
 */


public class Agenda {
    private String agendaId;
    private String agendaName;

    public Agenda(String agendaId, String agendaName) {
        this.agendaId = agendaId;
        this.agendaName = agendaName;
    }

    public String getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getAgendaName() {
        return agendaName;
    }

    public void setAgendaName(String agendaName) {
        this.agendaName = agendaName;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "agendaId='" + agendaId + '\'' +
                ", agendaName='" + agendaName + '\'' +
                '}';
    }
}
