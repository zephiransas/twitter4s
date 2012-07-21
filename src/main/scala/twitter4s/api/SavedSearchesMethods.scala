package twitter4s.api

import twitter4s.ResponseList
import twitter4j.SavedSearch

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
trait SavedSearchesMethods {
  /**
   * Returns the authenticated user's saved search queries.
   * <br />This method calls twitter4j.Twitter.getSavedSearches.
   * <br />getSavedSearchs calls http://api.twitter.com/1/saved_searches.json
   *
   * @return Returns an array of numeric user ids the authenticating user is blocking.
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/saved_searches">GET saved_searches | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def getSavedSearches: ResponseList[SavedSearch]

  /**
   * Retrieve the data for a saved search owned by the authenticating user specified by the given id.
   * <br />This method calls twitter4j.Twitter.showSavedSearches.
   * <br />showSavedSearches calls http://api.twitter.com/1/saved_searches/show/:id.json
   *
   * @param id The id of the saved search to be retrieved.
   * @return the data for a saved search
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/get/saved_searches/show/:id">GET saved_searches/show/:id | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def showSavedSearch(id: Int): SavedSearch

  /**
   * Creates a saved search for the authenticated user.
   * <br />This method calls twitter4j.Twitter.createSavedSearches.
   * <br />createSavedSearches calls http://api.twitter.com/1/saved_searches/saved_searches/create.json
   *
   * @param query the query string
   * @return the data for a created saved search
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/saved_searches/create">POST saved_searches/create | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def createSavedSearch(query: String): SavedSearch

  /**
   * Destroys a saved search for the authenticated user. The search specified by id must be owned by the authenticating user.
   * <br />This method calls twitter4j.Twitter.destroySavedSearches.
   * <br />destroySavedSearches calls http://api.twitter.com/1/saved_searches/destroy/id.json
   *
   * @param id The id of the saved search to be deleted.
   * @return the data for a destroyed saved search
   * @throws TwitterException when Twitter service or network is unavailable
   * @see <a href="https://dev.twitter.com/docs/api/1/post/saved_searches/destroy/:id">POST saved_searches/destroy/:id | Twitter Developers</a>
   * @since Twitter4S 1.0.0
   */
  def destroySavedSearch(id: Int): SavedSearch
}