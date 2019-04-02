package com.example.myapplication;

public class listSons {
    private int ID_mothers ,ID_sons;
    private String date;
    boolean Finished;





    public listSons(int ID_mothers ,int ID_sons ,String date ,boolean Finished ){
        this.ID_mothers=ID_mothers;
        this.ID_sons=ID_sons;
        this.date=date;
        this.Finished=Finished;

    }

    public int ID_mothers(){            return ID_mothers;        }
    public int ID_sons(){            return ID_sons;        }
    public String date(){            return date;        }
    public boolean Finished(){            return Finished;        }
}


