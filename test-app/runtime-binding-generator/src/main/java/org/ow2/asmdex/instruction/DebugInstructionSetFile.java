/* Software Name : AsmDex
 * Version : 1.0
 *
 * Copyright © 2012 France Télécom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holders nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.ow2.asmdex.instruction;

import org.ow2.asmdex.Opcodes;
import org.ow2.asmdex.lowLevelUtils.ByteVector;
import org.ow2.asmdex.structureWriter.ConstantPool;

/**
 * Class representing a Debug Instruction Set File.
 *
 * @author Julien Névo
 */
public class DebugInstructionSetFile extends DebugInstruction implements IDebugSourceNameInstruction {

    /**
     * The name of the source file.
     */
    protected String sourceName;

    /**
     * Constructor of the Debug Instruction.
     * @param sourceName name of the source file.
     */
    public DebugInstructionSetFile(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String getSourceName() {
        return sourceName;
    }

    @Override
    public int getOpcode() {
        return Opcodes.DBG_SET_FILE;
    }

    @Override
    public void write(ByteVector out, ConstantPool constantPool) {
        out.putByte(Opcodes.DBG_SET_FILE);
        out.putUleb128p1(sourceName == null ? Opcodes.NO_INDEX_SIGNED :
                         constantPool.getStringIndex(sourceName));
    }
}
