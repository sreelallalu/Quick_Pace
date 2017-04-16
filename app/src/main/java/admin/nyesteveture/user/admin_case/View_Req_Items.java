package admin.nyesteveture.user.admin_case;

/**
 * Created by lalu on 4/16/2017.
 */

public class View_Req_Items
{
    String Location;
    String Request_police;
    String ambulance;
    String FireForce;
    String Hospital;
    String subject;


    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getRequest_police() {
        return Request_police;
    }

    public void setRequest_police(String request_police) {
        Request_police = request_police;
    }

    public String getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(String ambulance) {
        this.ambulance = ambulance;
    }

    public String getFireForce() {
        return FireForce;
    }

    public void setFireForce(String fireForce) {
        FireForce = fireForce;
    }

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String hospital) {
        Hospital = hospital;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
