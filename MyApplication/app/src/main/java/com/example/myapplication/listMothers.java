package com.example.myapplication;

import java.util.Date;

public class listMothers {
    private int ID_mothers;
        private String date_Birth,sherbet,Ivomac,Intestinal,Etswing,vaccination;
        private boolean pregnant;




    public listMothers(int ID_mothers ,String date_Birth ,String sherbet,String Intestinal,String vaccination,String Etswing,String Ivomac,boolean pregnant){
            this.ID_mothers=ID_mothers;
            this.date_Birth=date_Birth;
            this.sherbet=sherbet;
            this.Ivomac=Ivomac;
            this.pregnant=pregnant;
            this.vaccination=vaccination;

            this.Intestinal=Intestinal;
            this.Etswing=Etswing;

        }

        public int ID_mothers(){            return ID_mothers;        }
        public String date_Birth(){            return date_Birth;        }
        public String sherbet(){            return sherbet;        }
        public String Ivomac(){            return Ivomac; }
        public String Intestinal(){ return Intestinal; }
    public String vaccination(){ return vaccination; }
         public String Etswing(){        return Etswing;    }
        public boolean pregnant(){            return pregnant;        }


    }