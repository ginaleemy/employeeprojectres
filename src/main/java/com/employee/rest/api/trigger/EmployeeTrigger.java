package com.employee.rest.api.trigger;



import java.sql.Connection;
import java.sql.SQLException;

import org.h2.api.Trigger;

public class EmployeeTrigger implements Trigger {
	public void init(String triggerName, Connection conn) throws SQLException {
		// Initialization code if needed
	}

	public void fire(Connection conn, String tableName, Object[] oldRow, Object[] newRow) throws SQLException {
		if (newRow != null) {
			// Set the employee_code for the new row
			Integer id = (Integer) newRow[0]; // Assuming id is the first column
			String employeeCode = String.format("EMP%04d", id);
			newRow[4] = employeeCode; // Assuming employee_code is the fifth column
		}
	}

	@Override
	public void close() {
		// Cleanup code if needed
	}

	public boolean isBefore() {
		return true; // This trigger is a BEFORE trigger
	}

	public boolean isUpdate() {
		return true; // This trigger responds to both INSERT and UPDATE
	}

	public boolean isInsert() {
		return true;
	}

	public boolean isDelete() {
		return false; // This trigger does not handle DELETE
	}

	@Override
	public void fire(Connection arg0, Object[] oldRow, Object[] newRow) throws SQLException {
		// TODO Auto-generated method stub
		if (newRow != null) {
			// Set the employee_code for the new row
			Integer id = (Integer) newRow[0]; // Assuming id is the first column
			String employeeCode = String.format("EMP%04d", id);
			newRow[4] = employeeCode; // Assuming employee_code is the fifth column
		}
	}
}
