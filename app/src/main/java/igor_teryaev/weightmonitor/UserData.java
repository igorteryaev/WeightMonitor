package igor_teryaev.weightmonitor;

import java.util.Date;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.Observable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by igor_teryaev on 06.03.2015.
 */


public class UserData extends BaseObservable{
    private int m_iD;
    private String userName;
    private Date m_dOB;
    private boolean m_sexIsMale;


    public UserData(int r_id, String r_username, Date r_dob, boolean r_sexIsMale)
    {
        this.m_iD = r_id;
        this.userName = r_username;
        this.m_dOB = r_dob;
        this.m_sexIsMale = r_sexIsMale;
 /*       addOnPropertyChangedCallback(new android.databinding.Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(android.databinding.Observable sender, int propertyId) {
                updateDependentViews();
            }
        });*/
    }

 /*   private void updateDependentViews() {
        Log.i("PropertyChamnged", "Propertychanged");
    }*/


    @Bindable
    public int getID()
    {
        return m_iD;
    }

    @Bindable
    public  String getUserName()
    {
        return this.userName;
    }

    @Bindable
    public Date getDOB()
    {
        return m_dOB;
    }

    @Bindable
    public boolean isSexIsMale()
    {
        return m_sexIsMale;
    }

    public void setUserName (String r_userName) {
        if(!this.userName.equals(r_userName)) {
            this.userName = r_userName;
         //   notifyPropertyChanged(igor_teryaev.weightmonitor.BR.userName);
        }
    }

    public void setID(int r_id){
        if(this.m_iD != r_id)
            this.m_iD = r_id;
    //    notifyPropertyChanged(igor_teryaev.weightmonitor.BR.iD);
    }

    public void setDOB(Date r_dob) {
        if(this.m_dOB != r_dob)
            this.m_dOB = r_dob;
    //    notifyPropertyChanged(igor_teryaev.weightmonitor.BR.dOB);
    }

    public void setSexIsMale(boolean r_sexIsMale){
        if(this.m_sexIsMale != r_sexIsMale)
            this.m_sexIsMale = r_sexIsMale;
        //notifyPropertyChanged(igor_teryaev.weightmonitor.BR.sexIsMale);
    }



}


