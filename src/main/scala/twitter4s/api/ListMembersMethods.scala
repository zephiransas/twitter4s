package twitter4s.api

import twitter4j.User
import twitter4j.UserList
import twitter4s.PagableResponseList

/**
 * @author Shinsuke Abe - mao.instalitfe at gmail.com
 */
trait ListMembersMethods {
  /**
   * Returns the members of the specified list.
   * <br />This method calls twitter4j.Twitter.getUserListMembers.
   * <br />getUserListMembers calls http://api.twitter.com/1/lists/members.json
   *
   * @param listId The id of the list
   * @param cursor Breaks the results into pages. A single page contains 20 lists. Provide a value of -1 to begin paging. Provide values as returned to in the response body's next_cursor and previous_cursor attributes to page back and forth in the list.
   * @return the members of the specified list.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/members">GET lists/members | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getUserListMembers(listId: Int, cursor: Long): PagableResponseList[User]

  /**
   * Adds a member to a list. The authenticated user must own the list to be able to add members to it. Lists are limited to having 500 members.
   * <br />This method calls twitter4j.Twitter.addUserListMember
   * <br />This method calls http://api.twitter.com/1/lists/members/create.json
   *
   * @param listId The id of the list.
   * @param userId The id of the user to add as a member of the list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/members/create">POST lists/members/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def addUserListMember(listId: Int, userId: Long): UserList

  /**
   * Adds multiple members to a list, by specifying a comma-separated list of member ids or screen names. The authenticated user must own the list to be able to add members to it. Lists are limited to having 500 members, and you are limited to adding up to 100 members to a list at a time with this method.
   * <br />This method calls twitter4j.Twitter.addUserListMembers.
   * <br />addUserListMembers calls http://api.twitter.com/1/lists/members/create_all.json
   * <br />Note1: You must set userIds or screenNames at least.
   * <br />Note2: Parameter userIds is taken priority over screenNames.
   *
   * @param listId (required) The id of the list.
   * @param userIds (optional) The array of ids of the user to add as member of the list. up to 100 are allowed in a single request.
   * @param screenNames (optional) The array of screen names of the user to add as member of the list. up to 100 are allowed in a single request.
   * @throws TwitterException when Twitter service or network is unavailable
   * @throws IellegalArgumentException when userIds and screenNames are not set
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/members/create_all">POST lists/members/create_all | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def addUserListMembers(listId: Int, userIds: Option[Array[Long]] = None, screenNames: Option[Array[String]] = None): UserList

  /**
   * Removes the specified member from the list. The authenticated user must be the list's owner to remove members from the list.
   * <br />This method calls twitter4j.Twitter.deleteUserListMember.
   * <br />deleteUserListMember calls http://api.twitter.com/1/lists/members/destroy.json
   *
   * @param listId The id of the list.
   * @param userId The screen name of the member you wish to remove from the list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/lists/members/destroy">POST lists/members/destroy | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def deleteUserListMember(listId: Int, userId: Long): UserList

  /**
   * Check if a user is a member of the specified list.
   * <br />This method calls twitter4j.Twitter.showUserListMembership.
   * <br />showUserListMembership calls http://api.twitter.com/1/lists/members/show.json
   *
   * @param listId The id of the list.
   * @param userId The id of the user who you want to know is a member or not of the specified list.
   * @return the updated list
   * @throws TwitterException when Twitter service or network is unavailable
   * , or the user is not a member of the specified list(TwitterException.getStatusCode() returns 404 in that case.)
   * @see <a href="https://dev.twitter.com/docs/api/1/get/lists/members/show">GET lists/members/show | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showUserListMembership(listId: Int, userId: Long): User
}