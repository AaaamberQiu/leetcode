import java.util.*;
import java.util.stream.Collectors;

public class Twitter {

    private Map<Integer, User> memory;

    private long globalTs;

    public Twitter() {
        this.memory = new HashMap<>();
        this.globalTs = 0L;
    }

    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, globalTs++);
        memory.computeIfAbsent(userId, User::new);
        User user = memory.get(userId);
        user.tweets.add(tweet);
    }

    public List<Integer> getNewsFeed(int userId) {
        if(!memory.containsKey(userId)) return new ArrayList<>();

        User user = memory.get(userId);
        PriorityQueue<Tweet> queue = new PriorityQueue<>((a,b) -> Long.compare(b.timestamp, a.timestamp));
        queue.addAll(user.tweets);
        for(User followee: user.follow){
            queue.addAll(followee.tweets);
        }

        int k = 10;
        List<Integer> ret = new ArrayList<>();
        while(!queue.isEmpty() && k > 0){
            ret.add(queue.poll().tweetId);
            k--;
        }

        return ret;

    }

    public void follow(int followerId, int followeeId) {
        memory.computeIfAbsent(followerId, User::new);
        memory.computeIfAbsent(followeeId, User::new);

        User follower = memory.get(followerId);
        User followee = memory.get(followeeId);

        follower.follow.add(followee);
    }

    public void unfollow(int followerId, int followeeId) {
        if(!memory.containsKey(followerId) || !memory.containsKey(followeeId)) return;
        User follower = memory.get(followerId);
        User followee = memory.get(followeeId);

        follower.follow.remove(followee);
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        twitter.getNewsFeed(1);// User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }
}


class User{
    int userId;
    Set<User> follow;
    List<Tweet> tweets;


    User(int userId){
        this.userId = userId;
        this.follow = new HashSet<>();
        this.tweets = new ArrayList<>();
    }
}

class Tweet{
    int tweetId;
    long timestamp;

    Tweet(int tweetId, long ts){
        this.tweetId = tweetId;
        this.timestamp = ts;
    }
}