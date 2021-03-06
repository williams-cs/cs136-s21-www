# cs136-s21-www
Web materials for Spring 2021 offering of CS 136

[Site URL](https://williams-cs.github.io/cs136-s21-www/)

## Directions
(These directions are from last year)

To update the schedule, first modify the appropriate fields in the file
`lecture-schedule.json`.
 * there is an entity for each "lecture" topic.
 * each lecture contains a dictionary with keys that describe the content type,
 and values that are "lists" of html. For example:
 ```JSON
 	{
	    "topic" : [
			"Course Overview/Aministration"
	    ],
	    "readings" : [
			"<i>Bailey</i> Ch. 0 (book overview)"
	    ],
	    "pre" : [
			"[<a href='https://docs.google.com/forms/d/e/1FAIpQLScXBTUIhPg5IQ35NbGAj2dgl-dYhU_hcjHQp8fctZfvVqEb2A/viewform?usp=sf_link'>Getting to Know You Form</a>]",
			"[<a href="https://www.youtube.com/watch?v=aUJEBcZswxk&list=PLvr5FsPW9lJ2CzjQens0VxAXTk0jLuFLe&index=3&t=0s">video</a>]"
	    ],
	    "materials" : [
			"[<a href='./resources/syllabus.pdf'>Syllabus</a>]",
			"[<aaa href=''>slides</a>]"
	    ],
	    "post" : [ ]
	},
 
 ```
 Each value is a list, and the pre/post values can be empty lists.
 
When you are done updating `lecture-schedule.json` with new material,
run `./update-schedule.sh` to overwrite `docs/schedule.html`, which
effecitvely updates the website.
