Feature: Get Tests on api/v1/jazzified/spotify/searchArtist

  Background:
    * url baseUrl
    * def artistPath = '/api/v1/jazzified/spotify/searchArtist'

  Scenario Outline: Get an Artist Response From Spotify API

    Given path artistPath
    And params {query: <query>, type: <type>}
    When method get
    Then status 200

    Examples:
    | query  | type
    | 'John' | 'artist'
    | 'Justin' | 'artist'
