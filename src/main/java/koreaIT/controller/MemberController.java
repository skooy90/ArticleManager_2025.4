package koreaIT.controller;

import koreaIT.dto.Member;
import koreaIT.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {

    private Scanner sc;
    private List<Member> memberList;
    private int lastMemberId = 3;

    public MemberController(Scanner sc) {
        this.sc = sc;
        this.memberList = new ArrayList<>();
    }

    public void doAction(String methodName, String cmd){

        switch (methodName){
            case "join":
                doJoin();
                break;
            case "list":
                showMember();
                break;
            default:
                System.out.println("명령어를 확인해주세요.4");
        }

    }

    public void showMember(){
        for (Member member : memberList){
            System.out.println(member.toString());
        }
    }

    public void doJoin() {

        String loginId;
        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine();

            if (isJoinableLoginId(loginId) == false) {
                System.out.println("이미 사용 중인 아이디입니다.");
                continue;
            }
            break;
        }

        String loginPw;
        while (true) {
            System.out.print("로그인 비밀번호 : ");
            loginPw = sc.nextLine();
            System.out.print("로그인 비밀번호 확인 : ");
            String loginPwConfirm = sc.nextLine();

            if (!loginPw.equals(loginPwConfirm)) {
                System.out.println("비밀번호 다시 확인해주세요.");
                continue;
            }
            break;
        }


        System.out.print("이름 : ");
        String name = sc.nextLine();

        String regDate = Util.getNowDate();

        lastMemberId++;
        Member addMember = new Member(lastMemberId, regDate, "", loginId, loginPw, name);

        memberList.add(addMember);

        System.out.printf("%d번 회원이 등록되었습니다. %s님 환영합니다.\n", lastMemberId, name);

    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : memberList) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }

    public void makeTestData() {
        System.out.println("테스트를 위한 회원 데이터를 생성합니다.");
        memberList.add( new Member(1, "2025-3-1", Util.getNowDate(), "admin", "admin", "관리자"));
        memberList.add( new Member(2, "2025-3-2", Util.getNowDate(), "test1", "test1", "회원1"));
        memberList.add( new Member(3, "2025-3-3", Util.getNowDate(), "test2", "test2", "회원2"));


    }
}