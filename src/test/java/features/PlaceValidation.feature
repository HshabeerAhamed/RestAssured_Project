Feature: Validating place APIs
  
  @Addplace
  Scenario Outline: verify if place is being successfully added using AddPlaceAPI
    Given Add place payload with "<Name>" "<Launguage>" "<Address>"
    When user calls "AddPlaceAPI" with "post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    Then Verify place_id create map to "<Name>" using "GetPlaceAPI"

    Examples:
     |Name| Launguage | Address |
     |Ahouse 		|English| Russia|
     #|Ahouse2 		|spanish| china|
   
   @Deleteplace  
  Scenario: Verify if delete place functionality is working
     Given DeletePlace Paylod
     When user calls "DeletePlaceAPI" with "POST" http request
     Then the API call got success with status code 200
     And "status" in response body is "OK"
    