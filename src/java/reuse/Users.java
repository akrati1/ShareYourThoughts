package reuse;

import java.util.ArrayList;

public class Users 
{
    private ArrayList<User> us;

    public Users() 
    {
        us=new ArrayList<User>();
    }

    public ArrayList<User> getUs() {
        return us;
    }

    public void setUs(ArrayList<User> us) {
        this.us = us;
    }
    
}
