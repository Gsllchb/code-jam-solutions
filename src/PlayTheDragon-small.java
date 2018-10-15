//GCJ Round 1A 2017: C small
import java.util.*;
import java.io.*;
import static java.lang.System.out;

enum Action {ATTACK, BUFF, CURE, DEBUFF}

class Status {
	int hd, ad, hk, ak;
	long turn;
	Action prev, prevPrev;
}

public class PlayTheDragon {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        final int T = in.nextInt();
        for (int i = 1; i <= T; ++i) {
            final int HD = in.nextInt();
            final int AD = in.nextInt();
            final int HK = in.nextInt();
            final int AK = in.nextInt();
            final int B = in.nextInt();
            final int D = in.nextInt();
            
            Status s = new Status();
            s.hd = HD;
            s.ad = AD;
            s.hk = HK;
            s.ak = AK;
            s.turn = 1;
            s.prev = Action.DEBUFF;
            s.prevPrev = Action.DEBUFF;
            Queue<Status> q = new LinkedList<Status>();
            q.offer(s);
            long step = 0;
            while (!q.isEmpty()) {
            	Status status = q.poll();
            	if (status.hd <= 0 || (status.prev == Action.CURE && status.prevPrev == Action.CURE)) {
            		continue;
            	}
            	Status nextStatus;
            	//defeat
            	if (status.ad >= status.hk) {
            		step = status.turn;
            		break;
            	}
            	//cure
            	if (status.hd <= status.ak) {
            		nextStatus = new Status();
            		nextStatus.hd = HD - status.ak;
            		nextStatus.ad = status.ad;
            		nextStatus.hk = status.hk;
            		nextStatus.ak = status.ak;
            		nextStatus.turn = status.turn + 1;
            		nextStatus.prev = Action.CURE;
            		nextStatus.prevPrev = status.prev;
            		q.offer(nextStatus);
            	} else {
	            	//attack
	            	if (status.ad > B) {
	            		nextStatus = new Status();
		        		nextStatus.hk = status.hk - status.ad;
		        		nextStatus.ad = status.ad;
		        		nextStatus.hd = status.hd - status.ak;
		        		nextStatus.ak = status.ak;
		        		nextStatus.turn = status.turn + 1;
		        		nextStatus.prev = Action.ATTACK;
		        		nextStatus.prevPrev = status.prev;
		        		q.offer(nextStatus);
	            	}
	        		//buff
	        		if (status.prev != Action.ATTACK && status.prevPrev != Action.ATTACK && B > 0) {
	        			nextStatus = new Status();
	        			nextStatus.ad = status.ad + B;
		        		nextStatus.hk = status.hk;
		        		nextStatus.hd = status.hd - status.ak;
		        		nextStatus.ak = status.ak;
		        		nextStatus.turn = status.turn + 1;
		        		nextStatus.prev = Action.BUFF;
		        		nextStatus.prevPrev = status.prev;
		        		q.offer(nextStatus);
	        		}
            	}
        		//debuff
        		if ((status.prev == Action.DEBUFF || (status.prev == Action.CURE && status.prevPrev == Action.DEBUFF))
        				&& D > 0 && status.ak != 0) {
        			nextStatus = new Status();
        			nextStatus.ak = status.ak - D;
        			if (nextStatus.ak < 0) {
        				nextStatus.ak = 0;
        			}
        			nextStatus.ad = status.ad;
	        		nextStatus.hk = status.hk;
	        		nextStatus.hd = status.hd - nextStatus.ak;
	        		nextStatus.turn = status.turn + 1;
	        		nextStatus.prev = Action.DEBUFF;
	        		nextStatus.prevPrev = status.prev;
	        		q.offer(nextStatus);
        		}
            }
            out.println("Case #" + i + ": " + (step == 0 ? "IMPOSSIBLE" : step));
        }
    }
}
