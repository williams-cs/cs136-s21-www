import sys
import json
import argparse
import re

class Lab:
    def __init__(self, sched_dict) :
        self._topic = sched_dict.get("topic", None)
        self._readings = sched_dict.get("readings", None)
        self._materials = sched_dict.get("materials", None)
        self._pre = sched_dict.get("pre", None)
        self._post = sched_dict.get("post", None)


class Meeting:
    def __init__(self, sched_dict) :
        self._topic = sched_dict.get("topic", None)
        self._readings = sched_dict.get("readings", None)
        self._materials = sched_dict.get("materials", None)
        self._pre = sched_dict.get("pre", None)
        self._post = sched_dict.get("post", None)

    def isLink(self, s) :
        '''returns true if text is a markdown link, i.e., [link text](url)'''
        return self.getLinkRe(s) != None

    def getLinkName(self, s) :
        '''return the text portion of the link'''
        # s must be a re match object (see getLinkRe function)
        return self.getLinkRe(s).group(1)

    def getLinkAddr(self, s) :
        '''return the url portion of the link'''
        # s must be a re match object (see getLinkRe function)
        return self.getLinkRe(s).group(2)

    def getLinkRe(self, s) :
        '''return an re.match object for "markdown" link format, i.e., [link text](url)'''
        ## code adapted from https://stackoverflow.com/a/23395483

        # Anything that isn't a square closing bracket
        name = "[^]]+"

        # Anythign that isn't a closing paren
        url = "[^)]+"

        markup_regex = '\s*\[({0})]\(\s*({1})\s*\)'.format(name, url)

        return re.match(markup_regex, s)

    def substLink(self, s):
        '''transforms a markdown link, returning an html link as a str'''
        # s must be a re.match object (see getLinkRe function)

        ## code below adapted from https://stackoverflow.com/a/23395483
        # One or more things that aren't a square closing bracket
        name = "[^]]+"
        # One or more things that aren't a closing paren
        url = "[^)]+"
        markup_regex = '\[({0})]\(\s*({1})\s*\)'.format(name, url)
        replace = '<a href="{0}">{1}</a>\n'.format(self.getLinkAddr(s), self.getLinkName(s))
        return re.sub(markup_regex, replace, s)

    def readings_md2html(self) :
        s = ""
        for reading in self._readings :
            if self.isLink(reading) :
                s += '\t<li>{0}'.format(self.substLink(reading))
            else :
                s += '\t<li>{}\n'.format(reading)
        return s

    def materials_md2html(self) :
        s = ""
        for handout in self._materials :
            if self.isLink(handout) :
                s += '\t<li>{0}'.format(self.substLink(handout))
            else :
                s += '\t<li>{}\n'.format(handout)
        return s

    def readingsHTML(self) :
        return "\t" + "<br>\t".join(self._readings) + "\n"

    def readingsYML(self):
        return '\n'.join(['    {}'.format(item) for item in self._readings])

    def preHTML(self) :
        if self._pre is not None and len(self._pre) > 0:
            return "<br/>\n".join(["\t<font color='#009933'>&thinsp;(pre)&thinsp;</font> {}\n".format(pre) for pre in self._pre]) + "<br/>\n"
        return ""

    def materialsHTML(self) :
        return "\t" + "\n<br/>\n\t".join(self._materials) + "\n"

    def postHTML(self) :
        if self._post is not None and len(self._post) > 0:
            return "\n<br/>\n" + "<br/>\n".join(["\t<font color='#ff8000'>(post)</font> {}\n".format(post) for post in self._post]) + "\n"
        return ""

    def materialsYML(self):
        return '\n'.join(['    {}'.format(item) for item in self._materials])

    def topicHTML(self) :
        return "\t" + "<br>\t".join(self._topic) + "\n"

    def topicYML(self):
        return self._topic

    def __repr__(self) :
        return "top: {}\rea: {}\nmat: {}\n".format(self._topic,
                                                   self._readings,
                                                   self._materials)

def read_dict(sched_path) :
    '''reads a json file and returns it as a dictionary'''
    with open(sched_path, 'r') as sched_file :
        return json.load(sched_file)



def build_schedule(sd) :
    '''
    sd: schedule dictionary.
    sd should have 2 high-level keys: *dates* and *meetings*.
      *dates* is a list of lists, where each sublist has two values: day, key 
               (key is either "#" for a normal meeting, or the key of a named meeting)
      *meetings* is an ordered list of regular meetings, with each list entry
               specifying topic, readings, pre, materials, post
    '''
    reg = sd["meetings"]
    reg_meetings = [Meeting(m) for m in reg]

    dates = sd["dates"]
    i = 0
    sched = ""
    for date,meeting_type in sd["dates"] :

        ## if date type is "#", it is a regular meeting
        if meeting_type == "#" :
            meeting = reg_meetings[i]
            i += 1
        else :
            meeting = Meeting(sd[meeting_type])

        sched += '<tr>\n'

        sched += '<!--Date-->\n'
        sched += '<td width="8%" align="left" valign="middle">\n'
        sched += '\t' + date + '\n'
        sched += '</td>\n'

        sched += '<!--Topic-->\n'
        sched += '<td width="16%" align="center">\n'
        sched += meeting.topicHTML()
        sched += '</td>\n'

        sched += '<!--Materials-->\n'
        sched += '<td wdith="18%">\n'
        sched += meeting.preHTML()
        sched += meeting.materialsHTML()
        sched += meeting.postHTML()
        sched += '</td>\n\n'


        sched += '<!--Readings-->\n'
        sched += '<td wdith="18%">\n'
        sched += meeting.readingsHTML()
        sched += '</td>\n'

        sched += '</tr>\n'
    return sched

def build_yml(sd, out=sys.stdout) :
    '''
    sd: schedule dictionary.
    sd should have 2 high-level keys: *dates* and *meetings*.
      *dates* is a list of lists, where each sublist has two values: day, key 
               (key is either "#" for a normal meeting, or the key of a named meeting)
      *meetings* is an ordered list of regular meetings, with each list entry
               specifying topic, readings, and materials
    '''

    reg = sd["meetings"]
    reg_meetings = [Meeting(m) for m in reg]

    dates = sd["dates"]
    i = 0
    sched = ""
    for date,meeting_type in sd["dates"] :

        ## if date type is "#", it is a regular meeting
        if meeting_type == "#" :
            meeting = reg_meetings[i]
            i += 1
            num = str(i)
        else :
            meeting = Meeting(sd[meeting_type])
            num = meeting_type


        # 2 spaces for tabs
        # put newlines at end of lines, not beginning
        print('- number: {}'.format(num), end='\n', file=out)
        print('  date: {}'.format(date), end='\n', file=out)
        print('  topic: {}'.format(meeting.topicYML()), end='\n', file=out)
        print('  reading: |+\n{}'.format(meeting.readingsYML()), end='\n', file=out)
        print('  materials: |+\n{}'.format(meeting.materialsYML()), end='\n', file=out)


if __name__ == "__main__" :
    parser = argparse.ArgumentParser()
    parser.add_argument("sched_json", type=str, help="schedule file (json)")
    parser.add_argument("sched_template", type=str, help="schedule file template (html)")
    parser.add_argument("--build_yml", dest="build_yml", default=False, action="store_true")
    parser.add_argument("--build_html", dest="build_html", default=True, action="store_true")
    args = parser.parse_args()

    if args.build_yml:
        build_yml(read_dict(args.sched_json))

    if args.build_html:
        with open(args.sched_template, 'r') as fin :
            for line in fin :
                if line.startswith("##FILL") :
                    print(build_schedule(read_dict(args.sched_json)))
                else :
                    print(line, end="")

