package twitter4s.api

import twitter4j.Status
import twitter4j.StatusUpdate
import twitter4s.ResponseList
import twitter4j.User
import twitter4j.Paging
import twitter4j.IDs

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait StatusMethods {
  /**
   * Returns a single status, specified by the id parameter below. The status's author will be returned inline.
   * <br />This method calls twitter4j.Twitter.showStatus.
   * <br />showStatus calls http://api.twitter.com/1/statuses/show
   *
   * @param id the numerical ID of the status you're trying to retrieve
   * @return a single status
   * @throws twitter4j.TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/show/:id">GET statuses/show/:id | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showStatus(id: Long): Status

  /**
   * Updates the authenticating user's status. A status update with text identical to the authenticating user's text identical to the authenticating user's current status will be ignored to prevent duplicates.
   * <br />This method calls twitter4j.Twitter.updateStatus.
   * <br />updateStatus calls http://api.twitter.com/1/statuses/update
   * <br />Note: You must set either status or latestStatus.
   *
   * @param status (optional) the text of your status update
   * @param latestStatus (optional) the latest status to be updated.
   * @return the latest status
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IllegalArgumentException when both of status and latestStatus are set or not.
   * @see <a href="https://dev.twitter.com/docs/api/1/post/statuses/update">POST statuses/update | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def updateStatus(status: Option[String] = None, latestStatus: Option[StatusUpdate] = None): Status

  /**
   * Destroys the status specified by the required ID parameter.<br />
   * Usage note: The authenticating user must be the author of the specified status.
   * <br />This method calls twitter4j.Twitter.destroyStatus.
   * <br />destroyStatus calls http://api.twitter.com/1/statuses/destroy
   *
   * @param statusId The ID of the status to destroy.
   * @return the deleted status
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/statuses/destroy/:id">POST statuses/destroy/:id | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroyStatus(statusId: Long): Status

  /**
   * Retweets a tweet. Returns the original tweet with retweet details embedded.
   * <br />This method calls twitter4j.Twitter.retweetStatus.
   * <br />retweetStatus calls http://api.twitter.com/1/statuses/retweet
   *
   * @param statusId The ID of the status to retweet.
   * @return the retweeted status
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/statuses/retweet/:id">POST statuses/retweet/:id | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def retweetStatus(statusId: Long): Status

  /**
   * Returns up to 100 of the first retweets of a given tweet.
   * <br />This method calls twitter4j.Twitter.getRetweets.
   * <br />getRetweets calls http://api.twitter.com/1/statuses/retweets
   *
   * @param statusId The numerical ID of the tweet you want the retweets of.
   * @return the retweets of a given tweet
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/retweets/:id">Tweets Resources › statuses/retweets/:id</a>
   * @since Twitter4S 1.0.0
   */
  def getRetweets(statusId: Long): ResponseList[Status]

  /**
   * Show user objects of up to 100 members who retweeted the status.
   * <br />This method calls twitter4j.Twitter.getRetweetedBy.
   * <br />getRetweetedBy calls http://api.twitter.com/1/statuses/:id/retweeted_by
   *
   * @param statusId The ID of the status you want to get retweeters of
   * @param paging (optional) controls pagination. Supports count and page parameters.
   * @return the list of users who retweeted your status
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/:id/retweeted_by">GET statuses/:id/retweeted_by | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getRetweetedBy(statusId: Long, paging: Option[Paging] = None): ResponseList[User]

  /**
   * Show user ids of up to 100 users who retweeted the status represented by id
   * <br />This method calls twitter4j.Twitter.getRetweetedByIDs.
   * <br />getRetweetedByIDs calls http://api.twitter.com/1/statuses/:id/retweeted_by/ids.format
   * 
   * @param statusId The ID of the status you want to get retweeters of
   * @param paging (optional) controls pagination. Supports count and page parameters.
   * @return IDs of users who retweeted the stats
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/statuses/:id/retweeted_by/ids">GET statuses/:id/retweeted_by/ids | Twitter Developers</a>
   * @since Twitter4J 1.0.0
   */
  def getRetweetedByIDs(statusId: Long, paging: Option[Paging] = None): IDs
}