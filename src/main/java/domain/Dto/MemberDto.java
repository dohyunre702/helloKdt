package domain.Dto;

public class MemberDto {
    private String name;
    private String email;
    private String organization;

    public MemberDto(String name, String email, String organization) {
        this.name = name;
        this.email = email;
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.name, this.email, this.organization);
        //toString() : 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드
    }

}
