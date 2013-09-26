package twitter4s

import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import org.specs2.mutable.Specification
import twitter4s.api.impl.SuggestedUsersResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class SuggestedUsersResourcesTest extends Specification with TwitterResourcesTestBase {

  type TargetResourcesType = SuggestedUsersResourcesImpl

  mockedTwitter4j.getSuggestedUserCategories returns FakeValuesUsedByMock.responseList[twitter4j.Category]
  mockedTwitter4j.getUserSuggestions(anyString) returns FakeValuesUsedByMock.responseList[twitter4j.User]
  mockedTwitter4j.getMemberSuggestions(anyString) returns FakeValuesUsedByMock.responseList[twitter4j.User]

  val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType
  
  "getSuggestedUserCategories" should {
    "call twitter4j getSuggestedUserCategories method" in {
      twitter.getSuggestedUserCategories.size must equalTo(1)
      there was one(mockedTwitter4j).getSuggestedUserCategories
    }
  }
  
  "getUserSuggestions" should {
    "call twitter4j getUserSuggestions method" in {
      twitter.getUserSuggestions("category slug").size must equalTo(1)
      there was one(mockedTwitter4j).getUserSuggestions("category slug")
    }
  }
  
  "getMemberSuggestions" should {
    "call twitter4j getMemberSuggestions method" in {
      twitter.getMemberSuggestions("category slug").size must equalTo(1)
      there was one(mockedTwitter4j).getMemberSuggestions("category slug")
    }
  }
}