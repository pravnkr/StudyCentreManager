package com.ignoubadhega.studycentremanager.dto;

public class SemesterDto {

    private short semNo;
    private String programme;

    public short getSemNo() {
        return semNo;
    }

    public void setSemNo(short semNo) {
        this.semNo = semNo;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    @Override
    public String toString() {
        return "SemesterDto [semNo="
            + semNo
            + ", programme="
            + programme
            + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result =
                prime * result
                        + ((programme == null) ? 0 : programme.hashCode());
        result = prime * result + semNo;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SemesterDto other = (SemesterDto) obj;
        if (programme == null) {
            if (other.programme != null)
                return false;
        } else if (!programme.equals(other.programme))
            return false;
        if (semNo != other.semNo)
            return false;
        return true;
    }
    
    

}
