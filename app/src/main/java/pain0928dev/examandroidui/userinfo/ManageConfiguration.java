package pain0928dev.examandroidui.userinfo;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pain0928 on 2018-03-04.
 */

public class ManageConfiguration {

    final String SP_USER_INFO = "SF_USER_INFO";

    final String USER_NAME = "user_name";
    final String USER_BIRTH = "user_birth";
    final String USER_GENDER = "user_gender";
    final String USER_BODYPART = "user_bodypart";

    private Context context;
    SharedPreferences spref;
    SharedPreferences.Editor spEditor;

    // Saved UserInfo
    private String userName;
    private String userBirth;                     // 1970-01-01
    private String userGender;                    // M, FM
    private String userBodyPart;                  // LL, RL

    public static ManageConfiguration getInstance(){
        if( manageConfiguration == null){
            manageConfiguration = new ManageConfiguration();
            return manageConfiguration;
        }
        return  manageConfiguration;
    }

    public void init(Context context){
        this.context = context;
        spref = context.getSharedPreferences(SP_USER_INFO, MODE_PRIVATE);
        spEditor = spref.edit();

        getInstance().loadUserInfo();
    }

    public ManageConfiguration loadUserInfo(){
        userName = spref.getString(USER_NAME, "");
        userBirth = spref.getString(USER_BIRTH, "");
        userGender = spref.getString(USER_GENDER, "");
        userBodyPart = spref.getString(USER_BODYPART, "");

        return ManageConfiguration.getInstance();
    }

    public String getUserName(){
        return userName;
    }

    public String getUserBirth(){
        return userBirth;
    }

    public String getUserGender(){
        return userGender;
    }

    public String getUserBodyPart(){
        return userBodyPart;
    }

    public void udpateUserName(String name){
        spEditor.putString(USER_NAME, name);
    }

    public void udpateUserBirth(String birth){
        spEditor.putString(USER_BIRTH, birth);
    }

    public void updateUserGender(String gender){
        spEditor.putString(USER_GENDER, gender);
    }

    public void setUserBodyPart(String bodyPart){
        spEditor.putString(USER_BODYPART, bodyPart);
    }

    private static ManageConfiguration manageConfiguration;
}
