package ca.ulaval.glo4002.devoir1.gestion.clinique.domaine.patient;

import java.util.Objects;

public class Patient {
    private String name;
    private int gravity;
    private VisibleSymptom visibleSymptom;

    public Patient(){

    }

    public Patient(String name, int gravity, VisibleSymptom visibleSymptom) {
        this.name = name;
        this.gravity = gravity;
        this.visibleSymptom = visibleSymptom;
    }


    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VisibleSymptom getVisibleSymptom() {
        return visibleSymptom;
    }

    public void setVisibleSymptom(VisibleSymptom visibleSymptom) {
        this.visibleSymptom = visibleSymptom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return gravity == patient.gravity && Objects.equals(name, patient.name) && visibleSymptom == patient.visibleSymptom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gravity, visibleSymptom);
    }
}
