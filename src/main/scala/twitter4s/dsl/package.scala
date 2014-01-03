package twitter4s
/*
 * Copyright (C) 2013 Shinsuke Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author mao.instantlife at gmail.com
 */
package object dsl {
  val idPrefix = "id:"

  implicit def twitter4SToTwitter4J(twitter: twitter4s.Twitter): twitter4j.Twitter = twitter.twitter4jObj

  implicit class TwitterDSLString(val sc: StringContext) extends AnyVal {
    def tweet(args: Any*) = TweetContext(sc.s(args: _*))

    def user(args: Any*) = UserContext(
      sc.s(args: _*) match {
        case str if str.toLowerCase.startsWith(idPrefix) => User.isSpecifiedBy(str.drop(idPrefix.length).toLong)
        case str => User.isSpecifiedBy(str)
      })

    def list(args: Any*) = ListContext(
      sc.s(args: _*) match {
        case str if str.toLowerCase.startsWith(idPrefix) => Right(str.drop(idPrefix.length).toInt)
        case str => Left(str)
      })

    def message(args: Any*) = MessageContext(sc.s(args: _*))
  }

  // context getter binding
  implicit val userContextGetter = UserContextGetter
  implicit val listContextGetter = ListContextGetter

  /**
   * Add user for any resource.
   *
   * @param user user string context
   * @return UserAdder instance
   */
  def add(user: UserContext) = UserAdder(user)

  /**
   * Get resource with specified context string from Twitter API.
   *
   * @param self Twitter4S DSL context instance
   * @param twitter Twitter4S instance
   * @param getter ContextGetter instance
   * @tparam C Twitter4S DSL context type
   * @tparam R Return value type
   * @return Context resources from API
   */
  def get[C, R](self: C)(implicit twitter: Twitter, getter: ContextGetter[C, R]):R = getter.get(self)

  /**
   * Send message to any user.
   *
   * @param messageContext message string context
   * @return MessageSender instance
   */
  def send(messageContext: MessageContext) = MessageSender(messageContext)

  def follow(userContext: UserContext)(implicit twitter: Twitter): twitter4s.User = userContext.user match {
    case Right(userId) => twitter.createFriendship(userId)
    case Left(screenName) => twitter.createFriendship(screenName)
  }

  case class UserAdder(userContext: UserContext) {
    def to(listContext: ListContext)(implicit twitter: Twitter): twitter4s.UserList =
      twitter.createUserListMember(get(listContext).id, get(userContext).id)
  }

  case class MessageSender(messageContext: MessageContext) {
    def to(userContext: UserContext)(implicit twitter: Twitter): twitter4s.DirectMessage = userContext.user match {
      case Right(userId) => twitter.sendDirectMessage(userId, messageContext.message)
      case Left(screenName) => twitter.sendDirectMessage(screenName, messageContext.message)
    }
  }
}
