package com.tuithemngot.model;

public class Import {

    private int stt;

    private Long import_id;


    private String import_date;


    private String Import_total;

    public Import() {
    }



    public Import(int stt, Long import_id, String import_date, String import_total){
        this.stt = stt;
        this.import_id = import_id;
        this.import_date = import_date;
        this.Import_total = import_total;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Long getImport_id() {
        return import_id;
    }

    public void setImport_id(Long import_id) {
        this.import_id = import_id;
    }

    public String getImport_date() {
        return import_date;
    }

    public void setImport_date(String import_date) {
        this.import_date = import_date;
    }

    public String getImport_total() {
        return Import_total;
    }

    public void setImport_total(String import_total) {
        Import_total = import_total;
    }
}
