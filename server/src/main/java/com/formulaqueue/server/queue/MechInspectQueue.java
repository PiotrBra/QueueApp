package com.formulaqueue.server.queue;

import com.formulaqueue.server.dao.MechInspectNumber;

import java.util.LinkedList;
import java.util.Queue;

public class MechInspectQueue{
    public Queue<MechInspectNumber> queue = new LinkedList<>();

    public Queue<MechInspectNumber> getQueue() {
        return queue;
    }
}
