

-- Insert Departments
INSERT INTO dept (name) VALUES 
    ('Information Technology'),
    ('Human Resources'),
    ('Finance'),
    ('Marketing'),
    ('Sales');

-- Insert Projects
INSERT INTO project (name, project_status) VALUES 
    ('Project Alpha', 'A'),
    ('Project Beta', 'A'),
    ('Project Gamma', 'C'),
    ('Project Delta', 'A'),
    ('Project Epsilon', 'A');

-- Insert Employees
INSERT INTO employee (name, position, department_id, employee_code) VALUES 
    ('John Doe', 'Software Engineer', 1, 'EMP0001'),
    ('Jane Smith', 'HR Manager', 2, 'EMP0002'),
    ('Michael Brown', 'Financial Analyst', 3, 'EMP0003'),
    ('Emily Davis', 'Marketing Specialist', 4, 'EMP0004'),
    ('James Wilson', 'Sales Executive', 5, 'EMP0005');


INSERT INTO employee (name, position, department_id) VALUES ('John Doe', 'Software Engineer', 1);
-- Insert Staff Projects
INSERT INTO employee_project (project_id, employee_code) VALUES 
    ( 1, 'EMP0001'),
    ( 2, 'EMP0002'),
    ( 3, 'EMP0003'),
    ( 4, 'EMP0004'),
    ( 5, 'EMP0005');