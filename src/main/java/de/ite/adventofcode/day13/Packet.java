package de.ite.adventofcode.day13;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class Packet implements Comparable<Packet> {

    @Getter
    private final String input;
    @Getter
    private final List<Packet> subPackets = new ArrayList<>();
    @Getter
    private int value;
    @Getter
    private boolean integer = true;

    public Packet(String input) {
        this.input = input;
        if(!input.startsWith("[")) {
            value = Integer.parseInt(this.input);
        } else {
            parseList(input);
        }
    }

    private void parseList(String input) {
        input = input.substring(1, input.length() - 1);
        int depth = 0;
        StringBuilder sb = new StringBuilder();
        integer = false;

        for(char ch : input.toCharArray()) {
            if(depth == 0 && ch == ',') {
                subPackets.add(new Packet(sb.toString()));
                sb = new StringBuilder();
            } else {
                if(ch == '[') {
                    depth++;
                } else if(ch == ']') {
                    depth--;
                }
                sb.append(ch);
            }
        }

        if(!sb.isEmpty()) {
            subPackets.add(new Packet(sb.toString()));
        }
    }


    @Override
    public int compareTo(Packet other) {
        if(this.isInteger() && other.isInteger()) {
            return Integer.compare(this.getValue(), other.getValue());
        } else if(!this.isInteger() && !other.isInteger()) {
            int minSize = Math.min(this.getSubPackets().size(), other.getSubPackets().size());
            for(int i = 0; i < minSize; i++) {
                Packet thisSubPacket = this.getSubPackets().get(i);
                Packet otherSubPacket = other.getSubPackets().get(i);
                int compareSubPacketResult = thisSubPacket.compareTo(otherSubPacket);
                if(compareSubPacketResult != 0) {
                    return compareSubPacketResult;
                }
            }
            return Integer.compare(this.getSubPackets().size(), other.getSubPackets().size());
        } else if(this.isInteger()) {
            return new Packet(String.format("[%s]", getValue())).compareTo(other);
        } else {
            return this.compareTo(new Packet(String.format("[%s]", other.getValue())));
        }
    }
}
