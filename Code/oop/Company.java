import java.util.ArrayList;

class Employee{
    private Company mainJob;
    private Company secondJob;

    public Employee(Company mainJob, Company secondJob){
        this.mainJob = mainJob;
        this.secondJob = secondJob;
    }
}

class Company{
    // employeesは従業員のリストを保持するための動的配列(多重度は*)
    private ArrayList<Employee> employeeList;

    // boardMembersは、会社の役員を表す。役員の数は最大10名(多重度は1-10)
    private BoardMember[] boardMemberList = new BoardMember[10];

    // parentCompanyは親会社を表す。親会社がない場合はnull (多重度は0-1)
    private Company parentCompany;

    // subsidiariesは子会社のリストを保持するための動的配列(多重度は*)
    private ArrayList<Company> subsidiaries;

    public void addEmployeeList(Employee employee){
        this.employeeList.add(employee);
    }

    public void setBoardMember(BoardMember boardMember, int position){
        this.boardMemberList[position] = boardMember;
    }

    public void setParentCompany(Company parentCompany){
        this.parentCompany = parentCompany;
    }

    public void addSubsidiaries(Company childCompany){
        this.subsidiaries.add(childCompany);
    }
}

class BoardMember {
    // 役員は最大で5つの会社を管理できる(多重度は1-5)

    private Company[] companiesManaging = new Company[5];

    public void setCompany(Company company, int position){
        companiesManaging[position] = company;
    }
}

class Main{
    public static void main(String[] args){
        Company company1 = new Company();
        Company company2 = new Company();


        Employee employee = new Employee(company1, company2);

        company1.addEmployeeList(employee);
        company2.addEmployeeList(employee);

        BoardMember boardMember = new BoardMember();
        company1.setBoardMember(boardMember, 0);

        boardMember.setBoardMember(company2, 0);

        company1.addSubsidiaries(company2);
        company2.setParentCompany(company1);


    }
}