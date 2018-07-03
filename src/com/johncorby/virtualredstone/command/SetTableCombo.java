package com.johncorby.virtualredstone.command;

import com.johncorby.virtualredstone.circuit.Instance;
import com.johncorby.virtualredstone.util.eventconversations.EventConversation;
import com.johncorby.virtualredstone.util.eventconversations.EventConversation.EventPrompt;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class SetTableCombo extends BaseCommand {
    SetTableCombo() {
        super("Add a table combo", "", "vrs.admin");
    }

    @Override
    public boolean onCommand(Player sender, String[] args) {
//        Conversation convo = new ConversationFactory(virtualRedstone)
//                .withPrefix(context -> "[SetTableCombo] ")
//                .withLocalEcho(false)
//                .withFirstPrompt(new StringPrompt() {
//                    @Override
//                    public String getPromptText(ConversationContext context) {
//                        return "How are you?";
//                    }
//
//                    @Override
//                    public Prompt acceptInput(ConversationContext context, String input) {
//                        context.getForWhom().sendRawMessage("Okay");
//                        return Prompt.END_OF_CONVERSATION;
//                    }
//                })
//                .buildConversation(sender);
//        convo.begin();

        EventConversation seq = new EventConversation(sender);
        seq.setFirstPrompt(seq.new EventPrompt<BlockBreakEvent>(BlockBreakEvent.class) {
            @Override
            protected String getPromptText() {
                seq.debug("getPromptText");
                return "Break a sign that you want to change the table combination of";
            }

            @Override
            protected boolean isForWhom(BlockBreakEvent input) {
                seq.debug("isForWhom");
                return input.getPlayer().equals(getForWhom());
            }

            @Override
            protected boolean isInputValid(BlockBreakEvent input) {
                seq.debug("isInputValid");
                Instance inst = Instance.get((Sign) input.getBlock().getState());
                if (inst == null) return false;
                setSessionData("inst", inst);
                input.setCancelled(true);
                return true;
            }

            @Override
            protected EventPrompt<BlockBreakEvent> acceptValidInput(BlockBreakEvent input) {
                seq.debug("acceptValidInput");
                Instance inst = (Instance) getSessionData("inst");
                getForWhom().sendRawMessage("Instance " + inst + " chosen");
                input.setCancelled(true);
                return null;
            }

            @Override
            protected String getInvalidInputText(BlockBreakEvent invalidInput) {
                seq.debug("getInvalidInputText");
                invalidInput.setCancelled(true);
                return "That's not a valid sign";
            }
        });
        seq.begin();
        return true;
    }
}
