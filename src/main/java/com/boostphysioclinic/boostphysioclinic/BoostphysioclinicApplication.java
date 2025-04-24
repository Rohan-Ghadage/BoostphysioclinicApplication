package com.boostphysioclinic.boostphysioclinic;

import com.boostphysioclinic.boostphysioclinic.model.Appointment;
import com.boostphysioclinic.boostphysioclinic.model.Patient;
import com.boostphysioclinic.boostphysioclinic.model.Physiotherapist;
import com.boostphysioclinic.boostphysioclinic.model.Treatment;
import com.boostphysioclinic.boostphysioclinic.service.ClinicManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BoostphysioclinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoostphysioclinicApplication.class, args);

		ClinicManager manager = new ClinicManager();
		manager.initializeData();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n===== Boost Physio Clinic System =====");
			System.out.println("1. Add Patient");
			System.out.println("2. Remove Patient");
			System.out.println("3. Book Appointment");
			System.out.println("4. Cancel Appointment");
			System.out.println("5. Attend Appointment");
			System.out.println("6. Print Report");
			System.out.println("0. Exit");
			System.out.print("Choose an option: ");

			int choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
				case 1 -> {
					System.out.print("ID: ");
					String id = scanner.nextLine();
					System.out.print("Name: ");
					String name = scanner.nextLine();
					System.out.print("Address: ");
					String address = scanner.nextLine();
					System.out.print("Phone: ");
					String phone = scanner.nextLine();
					boolean success = manager.addPatient(new Patient(id, name, address, phone));
					System.out.println(success ? "Patient added." : "Patient ID already exists.");
				}
				case 2 -> {
					System.out.print("Enter Patient ID to remove: ");
					boolean removed = manager.removePatient(scanner.nextLine());
					System.out.println(removed ? "Removed." : "Not found.");
				}
				case 3 -> {
					System.out.print("Enter Patient ID: ");
					String patientId = scanner.nextLine();
					Patient p = manager.getPatientById(patientId);
					if (p == null) {
						System.out.println("Patient not found.");
						break;
					}

					System.out.println("Search by: 1. Expertise  2. Physiotherapist Name");
					int searchType = Integer.parseInt(scanner.nextLine());

					List<Treatment> treatments = new ArrayList<>();
					if (searchType == 1) {
						System.out.print("Enter expertise: ");
						String exp = scanner.nextLine();
						for (Physiotherapist pt : manager.searchByExpertise(exp)) {
							treatments.addAll(pt.getTreatments());
						}
					} else {
						System.out.print("Enter physiotherapist name: ");
						Physiotherapist pt = manager.getPhysiotherapistByName(scanner.nextLine());
						if (pt != null) treatments.addAll(pt.getTreatments());
					}

					if (treatments.isEmpty()) {
						System.out.println("No available treatments.");
						break;
					}

					System.out.println("\nAvailable Treatments:");
					for (int i = 0; i < treatments.size(); i++) {
						Treatment t = treatments.get(i);
						System.out.printf("%d. %s (%s) with %s\n", i + 1, t.getName(), t.getTimeSlot().getDateTime(), t.getPhysiotherapist().getFullName());
					}

					System.out.print("Choose treatment number: ");
					int choiceIndex = Integer.parseInt(scanner.nextLine()) - 1;
					boolean booked = manager.bookAppointment(p, treatments.get(choiceIndex));
					System.out.println(booked ? "Appointment booked!" : "Conflict or already booked.");
				}
				case 4 -> {
					System.out.print("Enter Patient ID: ");
					String pid = scanner.nextLine();
					Patient patient = manager.getPatientById(pid);
					if (patient == null) {
						System.out.println("Patient not found.");
						break;
					}
					showAppointments(patient);
					System.out.print("Enter Treatment name to cancel: ");
					String treatmentName = scanner.nextLine();
					Treatment t = findTreatment(manager, treatmentName);
					boolean cancelled = manager.cancelAppointment(patient, t);
					System.out.println(cancelled ? "Cancelled." : "Not found or not booked.");
				}
				case 5 -> {
					System.out.print("Enter Patient ID: ");
					String pid = scanner.nextLine();
					Patient patient = manager.getPatientById(pid);
					if (patient == null) {
						System.out.println("Patient not found.");
						break;
					}
					showAppointments(patient);
					System.out.print("Enter Treatment name to attend: ");
					String treatmentName = scanner.nextLine();
					Treatment t = findTreatment(manager, treatmentName);
					boolean attended = manager.attendAppointment(patient, t);
					System.out.println(attended ? "Marked as attended." : "Not found or already attended.");
				}
				case 6 -> manager.generateReport();
				case 0 -> {
					System.out.println("Exiting system.");
					return;
				}
				default -> System.out.println("Invalid choice.");
			}
		}
	}

	private static void showAppointments(Patient patient) {
		System.out.println("Your Appointments:");
		for (Appointment a : patient.getAppointments()) {
			System.out.printf("- %s at %s [%s]\n", a.getTreatment().getName(), a.getTreatment().getTimeSlot().getDateTime(), a.getStatus());
		}
	}

	private static Treatment findTreatment(ClinicManager manager, String treatmentName) {
		for (Physiotherapist pt : manager.getPhysiotherapists()) {
			for (Treatment t : pt.getTreatments()) {
				if (t.getName().equalsIgnoreCase(treatmentName)) {
					return t;
				}
			}
		}
		return null;
	}

}
