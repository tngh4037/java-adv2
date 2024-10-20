package reflection;

import reflection.data.Team;
import reflection.data.User;

// 리플렉션 활용예시
public class FieldV3 {

    public static void main(String[] args) {
        User user = new User("id1", null, null);
        Team team = new Team("team1", null);

        System.out.println("===== before ====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);

        if (user.getId() == null) {
            user.setId("");
        }
        if (user.getName() == null) {
            user.setName("");
        }
        if (user.getAge() == null) {
            user.setAge(0);
        }

        if (team.getId() == null) {
            team.setId("");
        }
        if (team.getName() == null) {
            team.setName("");
        }

        System.out.println("===== after =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);
    }
}

// User, Team 객체에 입력된 정보 중에 null 데이터를 모두 기본 값으로 변경해야 한다고 가정해보자.
// 이 문제를 해결하려면 각각의 객체에 들어있는 데이터를 직접 다 찾아서 값을 입력해야 한다.
//
// 만약 User, Team 뿐만 아니라 Order, Cart, Delivery 등등 수 많은 객체에 해당 기능을 적용해야 한다면 매우 많은 번거로운 코드를 작성해야 할 것이다.
// 리플렉션을 활용하면 이 문제를 깔끔하게 해결할 수 있다.
