Feature: LoremIpsum

  Create test automation framework on Central Final Task on AQA <epam> couse winter'22
  Variant - Lorem Ipsum
  Owner - Hluzdovskyi Andrii
  Location - Kyiv

  Scenario Outline: Verify that the word correctly appears in the first paragraph
    Given Go to '<url>' page
    When Switch to '<language>' language using one of the links
    Then Verify that the text of the first  element, which is the first paragraph, contains the '<word>'
    Examples:
      | language   | word       | url                 |
      | Українська | риба       | https://lipsum.com/ |
      | Italiano   | utilizzato | https://lipsum.com/ |

  Scenario Outline: Verify that default setting result in text starting with Lorem ipsum
    Given Go to '<url>' page
    When Press “Generate Lorem Ipsum”
    Then Verify that the first paragraph starts with '<text>'
    Examples:
      | text                                                        |url                  |
      |Lorem ipsum dolor sit amet, consectetur adipiscing elit      | https://lipsum.com/ |

  Scenario Outline:Verify that Lorem Ipsum is generated with correct size
    Given Go to '<url>' page
    And Click on '<button name>' radio-button
    When Input <amount> into the number field
    And Press “Generate Lorem Ipsum”
    Then Verify the result has <amount> appropriate '<button name>' results
    Examples:
      |button name  |amount       | url                 |
      |words        |10           |https://lipsum.com/  |
      |lists        |12           |https://lipsum.com/  |
      |bytes        |100          |https://lipsum.com/  |
      |paragraphs   |5            |https://lipsum.com/  |

  Scenario Outline: Verify the checkbox
    Given Go to '<url>' page
    And Click on start checkbox
    When Press “Generate Lorem Ipsum”
    Then Verify that result no longer starts with '<text>'
    Examples:
      | text                                                        |url                  |
      |Lorem ipsum dolor sit amet, consectetur adipiscing elit      | https://lipsum.com/ |

  Scenario Outline: Check that randomly generated text paragraphs contain the word "lorem" with probability of less than 60%
    Given Go to '<url>' page
    When Input <amount> into the number field
    Then Check that the randomly generated text paragraphs containing the '<word>' less than limit if <amount> paragraphs each generate

      Examples:
        | amount      | word         | url                 |
        | 10          | lorem        | https://lipsum.com/ |
        | 5           | lorem        | https://lipsum.com/ |