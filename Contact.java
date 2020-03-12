package contacts;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    String name;
    String surname;
    String phoneNumber;

    public Contact(String name, String surname, String phoneNumber)
    {
        this.name=name;
        this.surname=surname;
        if (isValidPhoneNumber(phoneNumber))
            this.phoneNumber=phoneNumber;
        else {
            this.phoneNumber = "";
            System.out.println("Wrong number format!");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber ){
        //Split phone number into groups
        Pattern pattern = Pattern.compile("(-|\\s)");
        Matcher matcher = pattern.matcher(phoneNumber);
        ArrayList<Integer> sigIndex = new ArrayList<Integer>();
        ArrayList<String> groups = new ArrayList<String>();
        //System.out.println(phoneNumber);
        boolean flg=true;
        while (matcher.find())
        {
            sigIndex.add(matcher.start());
        }


        //System.out.println(sigIndex);
        if (sigIndex.size() > 0) {
            int start=0;
            int end=0;
            for (int i : sigIndex)
            {
                end=i;
                groups.add( phoneNumber.substring(start,i) );
                start=i+1;
            }
            groups.add(phoneNumber.substring(sigIndex.get(sigIndex.size() - 1) + 1, phoneNumber.length()));
        }
        else
            groups.add(phoneNumber);
        int cnt=0;//the number of ()
        for (int i=0;i<groups.size();i++)
        {
            String group = groups.get(i);
            if (group.contains("+")&& i!=0){
                flg=false;
                break;
            }

            //count the pairs of ()
            Pattern p =Pattern.compile("\\+?\\(+\\w+\\)+");
            Matcher m =p.matcher(group);
            if (m.find()) {
                cnt++;
                if (cnt>1){
                    flg=false;
                    break;
                }
            }

            //whether the () show in pairs
            if (group.contains("("))
            {
                if (!group.contains(")"))
                {
                    flg=false;
                    break;
                }
            }
            else {
                if (group.contains(")")){
                    flg=false;
                    break;
                }
            }

            //if the ( shows more than once
            p=Pattern.compile("((\\(\\w*){2,})|((\\w*\\)){2,})");
            m=p.matcher(group);
            if (m.find())
            {
                flg=false;
                break;
            }

            //if contains \\W
            p=Pattern.compile("[^\\+a-zA-Z0-9\\(\\)$]");
            m=p.matcher(group);
            if (m.find()){
                flg=false;
                break;
            }

        }
        return flg;
    }

    private boolean hasNumber( ){
        return !"".equals(this.phoneNumber);
    }







    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber))
            this.phoneNumber=phoneNumber;
        else {
            this.phoneNumber = "";
            System.out.println("Wrong number format!");
        }
    }

    public String getInfo()
    {
        String ph=hasNumber()? this.phoneNumber : "[No number]";
        String info = String.format("%s %s, %s", name, surname, ph);
        return info;
    }

}
