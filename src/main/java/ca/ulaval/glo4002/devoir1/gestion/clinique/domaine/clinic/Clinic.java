package ca.ulaval.glo4002.devoir1.gestion.clinique.domaine.clinic;

import ca.ulaval.glo4002.devoir1.gestion.clinique.domaine.patient.Patient;
import ca.ulaval.glo4002.devoir1.gestion.clinique.domaine.patient.VisibleSymptom;

import java.util.LinkedList;

public class Clinic {

    private LinkedList<Patient> medicineList = new LinkedList<>();
    private LinkedList<Patient> radiologyList = new LinkedList<>() ;
    boolean didTheTriage = true;

    public Clinic( ){

    }

    public Clinic(LinkedList<Patient> medicineList,LinkedList<Patient> radiologyList) {
        this.medicineList = medicineList;
        this.radiologyList = radiologyList;
    }


    public boolean triagePatient(String name, int gravity, VisibleSymptom visibleSymptom) {

            Patient priorityPatient = new Patient(name, gravity, visibleSymptom);
            Patient priorityPatient2 = new Patient(name, gravity, visibleSymptom);

            if (gravity > 5 ) {
                mettrePremierEnFileAttenteMedecine(priorityPatient);
            } else if(estVide() && priorityPatient.getVisibleSymptom() == VisibleSymptom.MIGRAINE){
                mettrePremierEnFileAttenteMedecine(priorityPatient);;
            }else if(priorityPatient.getVisibleSymptom() == VisibleSymptom.BROKEN_BONE) {
                mettreEnFileAttenteMedecine(priorityPatient);
                mettreEnFileAttenteRadiology(priorityPatient);
            } else if(priorityPatient2.getVisibleSymptom() == VisibleSymptom.FLU){
                medicineList.add(1,priorityPatient2);
            } else if(priorityPatient.getVisibleSymptom() == VisibleSymptom.SPRAIN) {
                mettrePremierEnFileAttenteMedecine(priorityPatient);;
                radiologyList.addFirst(priorityPatient);
            }

        return didTheTriage;
    }


    public boolean estVide() {
        return medicineList.isEmpty();
    }

    public boolean estDansFileRadiologie(Patient patient){
        return radiologyList.contains(patient);
    }

    public void mettreEnFileAttenteMedecine(Patient patient) {
        medicineList.add(patient);
    }

    public void mettrePremierEnFileAttenteMedecine(Patient patient) {
        medicineList.addFirst(patient);
    }

    public void mettreEnFileAttenteRadiology(Patient patient) {
        radiologyList.add(patient);
    }

    public Patient enleverDeLaFileAttente() {
        return medicineList.poll();
    }




}
