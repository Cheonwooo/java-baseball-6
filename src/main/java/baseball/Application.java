package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static int strike, ball;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("숫자 야구 게임을 시작합니다.");

        GameStart();
    }

    public static void GameStart() {
        strike = 0;
        ball = 0;

        try {
            List<Integer> computerNumber = ComputerSelectNumber();

            ExceptionList.validNumbers(computerNumber);
            while(strike!=3) {

                List<Integer> playerNumber = PlayerSelectNumber();
                ExceptionList.validNumbers(playerNumber);
                CompareNumber(computerNumber, playerNumber);

            }
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            ReStart();
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("게임이 종료되었습니다");
        }
    }

    public static void ReStart() {
        System.out.println("게임을 새로 시작려면 1, 종료하려면 2를 입력하세요.");

        String reStartNumber = Console.readLine();
        ExceptionList.validInputNumber(reStartNumber);

        if (reStartNumber.equals("1")) {
            GameStart();
            return;
        }
        System.out.println("게임이 종료되었습니다.");
    }

    public static List<Integer> ComputerSelectNumber() {

        List<Integer> computer = new ArrayList<>();

        while(computer.size()<3) {

            int randomNumber = Randoms.pickNumberInRange(1, 9);

                if(!computer.contains(randomNumber)) {
                    computer.add(randomNumber);
                }

        }
        return computer;
    }

    public static List<Integer> PlayerSelectNumber() {
        List<Integer> playerNumber = new ArrayList<>();
        System.out.print("숫자를 입력해주세요 : ");

        String player = Console.readLine().replaceAll(" ", "");

        for(int i=0; i<player.length(); i++) {
            playerNumber.add(player.charAt(i) - '0');
        }

        return playerNumber;
    }

    public static void CompareNumber(List<Integer> computerNumber, List<Integer> playerNumber) {
        strike = 0;
        ball = 0;

        for(int i=0; i<3; i++) {
            if (playerNumber.get(i).equals(computerNumber.get(i))) {
                strike++;
                continue;
            }
            if (computerNumber.contains(playerNumber.get(i))) {
                ball++;
                continue;
            }
        }
        System.out.println(PrintResult());
    }

    public static String PrintResult() {
        if(ball == 0 && strike == 0) {
            return "낫싱";
        }
        if(ball == 0) {
            return strike + "스트라이크";
        }
        if(strike==0) {
            return ball + "볼";
        }
        return ball + "볼 " + strike + "스트라이크";
    }
}
