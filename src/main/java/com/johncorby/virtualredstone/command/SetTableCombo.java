//package com.johncorby.virtualredstone.command;
//
//import com.johncorby.coreapi.command.BaseCommand;
//import com.johncorby.coreapi.util.eventconversation.EventConversation;
//import com.johncorby.coreapi.util.eventconversation.EventPrompt;
//import com.johncorby.coreapi.util.eventconversation.ListenerWithResult;
//import com.johncorby.virtualredstone.circuit.RedstoneSign;
//import com.johncorby.virtualredstone.circuit.Circuit;
//import org.bukkit.block.Block;
//import org.bukkit.block.Sign;
//import org.bukkit.entity.Player;
//import org.bukkit.event.block.BlockBreakEvent;
//import org.bukkit.event.player.AsyncPlayerChatEvent;
//import org.bukkit.event.player.PlayerEvent;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class SetTableCombo extends BaseCommand {
//    public SetTableCombo() {
//        super("Add a table combo", "", PERM_ADMIN);
//    }
//
//    @Override
//    public boolean onCommand(Player sender, String[] args) {
////        Conversation convo = new ConversationFactory(virtualRedstone)
////                .withPrefix(context -> "[SetTableCombo] ")
////                .withLocalEcho(false)
////                .withFirstPrompt(new StringPrompt() {
////                    @Override
////                    public String getPromptText(ConversationContext context) {
////                        return "How are you?";
////                    }
////
////                    @Override
////                    public Prompt acceptInput(ConversationContext context, String input) {
////                        context.getForWhom().sendRawMessage("Okay");
////                        return Prompt.END_OF_CONVERSATION;
////                    }
////                })
////                .buildConversation(sender);
////        convo.begin();
//
//        EventConversation convo = new EventConversation(sender);
//        convo.setFirstPrompt(new EventPrompt<>(BlockBreakEvent.class, convo, BlockBreakEvent::getPlayer) {
//            com.johncorby.virtualredstone.table.Circuit tab;
//            ListenerWithResult signToggle;
//
//
//            @Override
//            protected String getPromptText() {
//                convo.debug("getPromptText");
//                return "Break a sign that you want to change the table combination of";
//            }
//
//            @Override
//            protected boolean isInputValid(BlockBreakEvent input) {
//                convo.debug("isInputValid");
//                input.setCancelled(true);
//
//                if (!(input.getBlock().getState() instanceof Sign)) return false;
//                Sign sign = (Sign) input.getBlock().getState();
//
//                Circuit stat = com.johncorby.virtualredstone.table.Circuit.get(sign);
//                if (stat instanceof com.johncorby.virtualredstone.table.Circuit) {
//                    tab = (com.johncorby.virtualredstone.table.Circuit) stat;
//                    return true;
//                }
//                return false;
//            }
//
//
//            @Override
//            protected String getInvalidInputText(BlockBreakEvent invalidInput) {
//                convo.debug("getInvalidInputText");
//                return "That's not a valid sign";
//            }
//
//
//            @Override
//            protected EventPrompt acceptValidInput(BlockBreakEvent input) {
//                convo.debug("acceptValidInput");
//                getForWhom().sendRawMessage("Table " + tab + " chosen");
//
//                return new EventPrompt<>(AsyncPlayerChatEvent.class, convo, PlayerEvent::getPlayer) {
//                    final Set<Integer> inputs = new HashSet<>();
//                    final Set<Integer> outputs = new HashSet<>();
//
//
//                    @Override
//                    protected String getPromptText() {
//                        signToggle = new ListenerWithResult<>(BlockBreakEvent.class) {
//                            RedstoneSign sign;
//
//                            @Override
//                            public boolean execute(BlockBreakEvent event) {
//                                event.setCancelled(true);
//                                if (!(event.getBlock().getState() instanceof Sign)) return false;
//
//                                sign = RedstoneSign.get((Sign) event.getBlock().getState());
//                                return sign != null && sign.getParent().getParent().equals(tab);
//                            }
//
//                            @Override
//                            public void onSucceed(BlockBreakEvent event) {
//                                sign.setActive(!sign.isActive());
//                                event.getPlayer().sendRawMessage("Set " + sign + " to " + sign.isActive());
//                                //if (sign.isActive()) {
//                                //    if (sign instanceof Input) inputs.add(sign.get());
//                                //    else outputs.add(sign.get());
//                                //} else {
//                                //    if (sign instanceof Input) inputs.remove(sign.get());
//                                //    else outputs.remove(sign.get());
//                                //}
//                            }
//
//                            @Override
//                            public void onFail(BlockBreakEvent event) {
//                                event.getPlayer().sendRawMessage("That's not a valid sign");
//                            }
//                        };
//                        signToggle.register();
//                        return "Click on valid signs to add/remove them to/from the combo. When you're done, say anything in chat.";
//                    }
//
//                    @Override
//                    protected boolean isInputValid(AsyncPlayerChatEvent input) {
//                        input.setCancelled(true);
//                        return true;
//                    }
//
//
//                    @Override
//                    protected String getInvalidInputText(AsyncPlayerChatEvent input) {
//                        return null;
//                    }
//
//
//                    @Override
//                    protected EventPrompt acceptValidInput(AsyncPlayerChatEvent input) {
//                        getForWhom().sendRawMessage("Added table combo for Table " + tab);
//                        tab.combos.put(inputs, outputs);
//                        signToggle.unregister();
//                        return null;
//                    }
//                };
//            }
//        });
//        convo.create();
//        return true;
//    }
//}
