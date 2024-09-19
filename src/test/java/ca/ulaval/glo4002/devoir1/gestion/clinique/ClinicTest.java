package ca.ulaval.glo4002.devoir1.gestion.clinique;

import ca.ulaval.glo4002.devoir1.gestion.clinique.domaine.clinic.Clinic;
import ca.ulaval.glo4002.devoir1.gestion.clinique.domaine.patient.Patient;
import ca.ulaval.glo4002.devoir1.gestion.clinique.domaine.patient.VisibleSymptom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class ClinicTest {
    // create instance of Random class
    Random rand = new Random();

    // Generate random integers in range 0 to 999
    int g = rand.nextInt(1000);

    @Test
    public void clinicEstInitialementVide() {
        Clinic clinic = new Clinic();
        Assertions.assertTrue(clinic.estVide());
    }

    @Test
    public void quandmettreEnFileAttenteMedecine_devraitAjouterPatient() {
        Clinic clinic = new Clinic();
        Patient patient = new Patient();

        clinic.mettreEnFileAttenteMedecine(patient);
        Assertions.assertFalse(clinic.estVide());
    }

    @Test
    public void unPatient_quandEnleverDeLaFileAttente_devraitEtreRetirer() {
        Clinic clinic = new Clinic();
        Patient patient = new Patient();

        clinic.mettreEnFileAttenteMedecine(patient);
        clinic.enleverDeLaFileAttente();

        Assertions.assertTrue(clinic.estVide());

    }

    @Test
    public void unPatient_quandEnleverDeLaFileAttente_devraitRetournerPatient(){
        Clinic clinic = new Clinic();
        Patient patient = new Patient();
        clinic.mettreEnFileAttenteMedecine(patient);

        Assertions.assertEquals(patient, clinic.enleverDeLaFileAttente());
    }

    @Test
    public void plusieursPatients_quandEnleverDeLaFileAttente_devraitRetournerEnOrdre(){
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient();
        Patient patient2 = new Patient();

        clinic.mettreEnFileAttenteMedecine(patient1);
        clinic.mettreEnFileAttenteMedecine(patient2);

        Assertions.assertEquals(patient1, clinic.enleverDeLaFileAttente());
        Assertions.assertEquals(patient2, clinic.enleverDeLaFileAttente());

    }

    @Test
    public void unPatient_quandGraviteEstSuperiereACinq_estPrioritaireDansListeAttente(){
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient("Jess",g,VisibleSymptom.COLD);

        clinic.mettreEnFileAttenteMedecine(patient1);

        Assertions.assertTrue(clinic.triagePatient(patient1.getName(), patient1.getGravity(), patient1.getVisibleSymptom()));

    }

    @Test
    public void unPatient_quandSymptomEstBrokenBone_estMisDansFileRadiologie(){
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient("Jess",2,VisibleSymptom.BROKEN_BONE);

        Assertions.assertTrue(clinic.triagePatient(patient1.getName(), patient1.getGravity(), patient1.getVisibleSymptom()));
    }

    @Test
    public void unPatient_quandSymptomEstSprain_estMisDansFileRadiologie(){
        Clinic clinic = new Clinic();
        Patient patient2 = new Patient("Joe",4,VisibleSymptom.SPRAIN);

        Assertions.assertTrue(clinic.triagePatient(patient2.getName(), patient2.getGravity(), patient2.getVisibleSymptom()));

    }

    @Test
    public void unPatient_quandSymptomEstMigraine_estPrioritaireDansFileMedecine(){
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient("Jess",2,VisibleSymptom.MIGRAINE);

        Assertions.assertTrue(clinic.estVide());
        Assertions.assertTrue(clinic.triagePatient(patient1.getName(), patient1.getGravity(), patient1.getVisibleSymptom()));
        Assertions.assertFalse(clinic.estDansFileRadiologie(patient1));
    }

    @Test
    public void plusieursPatient_quandSymptomDuPatientEstFlu_estDeuxiemeDansFileAttente(){
        Clinic clinic = new Clinic();
        Patient patient1 = new Patient("Jess",2,VisibleSymptom.MIGRAINE);
        Patient patient2 = new Patient("Jess",2,VisibleSymptom.FLU);

        clinic.mettreEnFileAttenteMedecine(patient1);

        Assertions.assertTrue(clinic.triagePatient(patient2.getName(), patient2.getGravity(), patient2.getVisibleSymptom()));
        Assertions.assertFalse(clinic.estDansFileRadiologie(patient2));
    }

    @Test
    public void unPatient_quandSymptomEstSprain_estMisPremierDansFileRadiologieEtFileMedecine(){
        Clinic clinic = new Clinic();
        Patient patient2 = new Patient("Joe",g,VisibleSymptom.SPRAIN);

        Assertions.assertTrue(clinic.triagePatient(patient2.getName(), patient2.getGravity(), patient2.getVisibleSymptom()));

    }

}
