import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Bot {

    public static void main(String[] args) throws InterruptedException {

        // Loads .env file and stores all values into system properties
        Dotenv dotenv = Dotenv.configure()
                .systemProperties()
                .load();

        // Bot Token
        String token = System.getProperty("DISCORD_TOKEN");

        // JDA Builder
        JDA jda = JDABuilder.createDefault(token)
                .setStatus(OnlineStatus.ONLINE)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setMemberCachePolicy(MemberCachePolicy.ALL)

                // Event listeners (new instances of other classes extending ListenerAdapter)
                .addEventListeners(new Register(),new Trigger())

                .build()
                .awaitReady();

        System.out.println("Active bot: " + jda.getSelfUser().getName() + "#" + jda.getSelfUser().getDiscriminator());
    }
}
