package com.karsonnichols.model.replace.replaceText;

public class ReplaceText {
    private String replaceFor;
    private String replaceTo;

    public ReplaceText() {

    }

    public void setReplaceFor (String replaceFor) {
        this.replaceFor = replaceFor;
    }

    public String getReplaceFor() {
        return this.replaceFor;
    }

    public void setReplaceTo (String replaceTo) {
        this.replaceTo = replaceTo;
    }

    public String getReplaceTo () {
        return  this.replaceTo;
    }
}
